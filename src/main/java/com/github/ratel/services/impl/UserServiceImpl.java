package com.github.ratel.services.impl;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.enums.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.exceptions.statuscode.StatusCode;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.repositories.UserRepository;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.UserService;
import com.github.ratel.services.VerificationTokenService;
import com.github.ratel.utils.transfer_object.UserTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.ratel.exceptions.EntityNotFoundException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.email.text}")
    private String textMessageEmail;

    @Value("${app.verification.domain}")
    private String verificationDomain;

    private final EmailService emailService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final VerificationTokenService tokenService;

    @Autowired
    public UserServiceImpl(EmailService emailService, UserRepository userRepository, PasswordEncoder passwordEncoder, VerificationTokenService tokenService) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(StatusCode.NOT_FOUND));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EntityNotFoundException {
        User user = this.findUserByEmail(email);
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        throw new ConfirmPasswordException(("Пароль не верный"));
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    public UserResponse createUser(UserDto payload) {
        checkUserByEmail(payload.getEmail());
        User user = new User();
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.setEmail(payload.getEmail());
        String userPass = checkPassAndConfirmPass(payload.getPassword(), payload.getConfirmPassword());
        user.setPassword(this.passwordEncoder.encode(userPass));
        user.setPhone(payload.getPhone());
        user.setRoles(Roles.ROLE_USER);
        user.setVerification(UserVerificationStatus.UNVERIFIED);
        user.setStatus(EntityStatus.on);
        var userToken = UUID.randomUUID().toString();
        var userPattern = String.format(
                this.textMessageEmail,
                payload.getFirstname() + " " + payload.getLastname(),
                "верификации ",
                this.verificationDomain,
                "/verification?token=", userToken);
        this.emailService.sendMessageToEmail(
                payload.getEmail(),
                "Верификация пользователя App_Shop",
                userPattern
        );
        this.userRepository.save(user);
        this.tokenService.create(new VerificationToken(user, userToken));
        return UserTransferObject.fromUser(user);
    }

//    public User changeUserInfo(long userId, UserRegDto userRegDto) {
//        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));
//        user.setFirstname(userRegDto.getFirstname());
//        user.setLastname(userRegDto.getLastname());
//        user.setEmail(userRegDto.getEmail());
//        user.setLogin(userRegDto.getLogin());
//        user.setPassword(userRegDto.getPassword());
//        user.setPhone(userRegDto.getPhone());
//        user.setAddress(userRegDto.getAddress());
//        user.setUpdatedAt(new Date());
//        return this.userRepository.save(user);
//    }

    @Override
    public void deleteUserById(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow();
        if (user.getStatus().equals(EntityStatus.on)) {
            user.setStatus(EntityStatus.off);
            this.updateUser(user);
        } else {
            throw new EntityNotFoundException("Пользователь удален");
        }
    }

    public void checkUserByEmail(String email) {
        if (this.userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
    }

    public String checkPassAndConfirmPass(String pass, String confirmPass) {
        if (pass.equals(confirmPass)) {
            return confirmPass;
        } else {
            throw new ConfirmPasswordException("Пароли не совпадают");
        }
    }
}

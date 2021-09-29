package com.github.ratel.services.impl;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.repositories.RoleRepository;
import com.github.ratel.repositories.UserRepository;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.UserService;
import com.github.ratel.services.VerificationTokenService;
import com.github.ratel.utils.transfer_object.UserTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Value("${app.email.text}")
    private String textMessageEmail;

    @Value("${app.verification.domain}")
    private String verificationDomain;

    private final EmailService emailService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final VerificationTokenService tokenService;

    @Autowired
    public UserServiceImpl(EmailService emailService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, VerificationTokenService tokenService) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User findUserById(long userId) {
       return userRepository.getById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws EntityNotFoundException {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
        if (Objects.nonNull(user)) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
     throw new EntityNotFoundException(("Нет такого пользователя"));
    }

    @Override
    public void createManager(ManagerRequest payload) {
        User user = this.userRepository.findById(payload.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
        Roles userRoles = this.roleRepository.getById(2L);
        user.setRoles(Set.of(userRoles));
        user.setUpdatedAt(new Date());
        this.userRepository.save(user);
    }

    @Override
    public void saveAdmin(CreateAdminRequest payload) throws UserAlreadyExistException, ConfirmPasswordException {
        checkUserByEmail(payload.getEmail());
        User user = new User();
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.setEmail(payload.getEmail());
        String pass = checkPassAndConfirmPass(payload.getPassword(), payload.getConfirmPassword());
        user.setPassword(this.passwordEncoder.encode(pass));
        user.setCreatedAt(new Date());
        Roles userRole = this.roleRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
        user.setRoles(Set.of(userRole));
        user.setVerification(UserVerificationStatus.UNVERIFIED);
        user.setStatus(EntityStatus.on);
        var token = (UUID.randomUUID().toString());
        var pattern = String.format(
                this.textMessageEmail,
                payload.getFirstname() + " " + payload.getLastname(),
                "верификации ",
                this.verificationDomain,
                "/verification?token=", token);
        this.emailService.sendMessageToEmail(
                user.getEmail(),
                "Верификация пользователя App_Shop",
                pattern
        );
        this.userRepository.save(user);
        this.tokenService.create(new VerificationToken(user, token));
    }

    @Override
    public void updateUser(User user) {
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public UserResponse createUser(UserDto payload) {
        checkUserByEmail(payload.getEmail());
        User user = new User();
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.setEmail(payload.getEmail());
        String userPass = checkPassAndConfirmPass(payload.getPassword(), payload.getConfirmPassword());
        user.setPassword(this.passwordEncoder.encode(userPass));
        user.setPhone(payload.getPhone());
        user.setCreatedAt(new Date());
        Roles roles = this.roleRepository.findByRole("ROLE_USER")
                .orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
        user.setRoles(Set.of(roles));
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
        return UserTransferObject.fromUserReg(user);
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
    public void deleteUserById(long userId) {
        User user = this.userRepository.getById(userId);
        if(user.getStatus().equals(EntityStatus.on)) {
            user.setStatus(EntityStatus.off);
            this.updateUser(user);
        } else {
           throw new EntityNotFoundException("Пользователь удален");
        }
    }

    private void checkUserByEmail(String email) {
        if(this.userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException();
        }
    }

    public String checkPassAndConfirmPass(String pass, String confirmPass) {
        if(pass.equals(confirmPass)) {
            return confirmPass;
        } else {
            throw new ConfirmPasswordException("Пароли не совпадают");
        }
    }
}

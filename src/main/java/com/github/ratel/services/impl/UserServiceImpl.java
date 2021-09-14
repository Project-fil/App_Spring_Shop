package com.github.ratel.services.impl;

import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.repositories.RoleRepository;
import com.github.ratel.repositories.UserRepository;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl {

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

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User findUserById(long userId) {
       return userRepository.getById(userId);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFound("Нет такого пользователя"));
    }

    public User findByEmailAndPassword(String email, String password) {
        User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFound("Нет такого пользователя"));
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
     throw new EntityNotFound("Entity not found in db");
    }

//    @Transactional
    public User saveUser(User user) {
        Roles userRoles = this.roleRepository.getById(3L);
        user.setRoles(Set.of(userRoles));
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public void saveAdmin(CreateAdminRequest payload) throws UserAlreadyExistException {
        checkUserByEmail(payload.getEmail());
        User user = new User();
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.setEmail(payload.getEmail());
        String pass = checkPassAndConfirmPass(payload.getPassword(), payload.getConfirmPassword());
        user.setPassword(this.passwordEncoder.encode(pass));
        user.setCreatedAt(new Date());
        Roles userRole = this.roleRepository.getById(1L);
        user.setRoles(Set.of(userRole));
        user.setVerification(UserVerificationStatus.UNVERIFIED);
        user.setStatus(EntityStatus.on);
        var token = (UUID.randomUUID().toString());
        var pattern = String.format(
                this.textMessageEmail, "verification ",
                this.verificationDomain,
                "/verification?token=", token);
        this.emailService.sendMessageToEmail(user.getEmail(), "Verification user",
                pattern);
        this.userRepository.save(user);
        this.tokenService.create(new VerificationToken(user, token));
    }

    public void updateUser(User user) {
        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

//    public long createUser(UserRegDto userRegDto) {
//        User user = new User();
//        user.setFirstname(userRegDto.getFirstname());
//        user.setLastname(userRegDto.getLastname());
//        user.setEmail(userRegDto.getEmail());
//        user.setLogin(userRegDto.getLogin());
//        user.setPassword(userRegDto.getPassword());
//        user.setPhone(userRegDto.getPhone());
//        user.setAddress(userRegDto.getAddress());
//        doesUserExist(user.getId());
//        return this.userRepository.save(user).getId();
//    }

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

    public void deleteUserById(long userId) {
        User user = this.userRepository.getById(userId);
        if(user.getStatus().equals(EntityStatus.on)) {
            user.setStatus(EntityStatus.off);
            this.updateUser(user);
        } else {
           throw new EntityNotFound("User has been deleted");
        }
    }

    private void doesUserExist(long userId) {
        if (this.userRepository.findById(userId).isPresent()) {
            throw new RuntimeException("This user already exists!");
        }
    }

    private void checkUserByEmail(String email) {
        if(Objects.nonNull(this.userRepository.findByEmail(email).orElse(null))) {
            throw new UserAlreadyExistException();
        }
    }

    private String checkPassAndConfirmPass(String pass, String confirmPass) {
        if(pass.equals(confirmPass)) {
            return confirmPass;
        } else {
            throw new ConfirmPasswordException();
        }
    }
}

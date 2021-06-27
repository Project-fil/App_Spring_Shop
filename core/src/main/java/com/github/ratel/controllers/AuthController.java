package com.github.ratel.controllers;

import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.dto.VerificationDto;
import com.github.ratel.entity.User;
import com.github.ratel.exception.UserAlreadyExistException;
import com.github.ratel.payload.UserVerificationStatus;
import com.github.ratel.security.AuthResponse;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.services.impl.EmailService;
import com.github.ratel.services.impl.UserService;
import com.github.ratel.utils.TransferObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider, UserService userService, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/registration")
    public HttpStatus registration(@RequestBody @Valid UserRegDto payload) {
        if (userService.findByLogin(payload.getLogin()) != null) {
            throw new UserAlreadyExistException("User already exist");
        }
        User user = TransferObj.toUser(payload);
        emailService.sendMessageToEmail(user.getEmail(), "Verification user",
                "Follow the link for verification: http://localhost:8083/authorization/verification");
        userService.saveUser(user);
        return HttpStatus.OK;
    }

    @PostMapping("/authorization")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse auth(@RequestBody @Valid UserAuthDto userAuthDto) {
        User user = userService.findByLoginAndPassword(userAuthDto.getLogin(), userAuthDto.getPassword());
        String token = null;
        if (user.getVerification().equals(UserVerificationStatus.UNVERIFIED)) {
            log.error("You are not verified");
        } else if (user.getVerification().equals(UserVerificationStatus.VERIFIED)) {
            token = tokenProvider.generateToken(user.getLogin());
        }
        return new AuthResponse(token);
    }

    @PostMapping("/authorization/verification")
    @ResponseStatus(HttpStatus.OK)
    public void passingVerification(@RequestBody @Valid UserAuthDto verificationDto) {
        User user = userService.findByLogin(verificationDto.getLogin());
        if (passwordEncoder.matches(verificationDto.getPassword(), user.getPassword())) {
            user.setVerification(UserVerificationStatus.VERIFIED);
            userService.updateUser(user);
        } else {
            throw new NullPointerException();
        }
    }
}

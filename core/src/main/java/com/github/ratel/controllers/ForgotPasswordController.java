package com.github.ratel.controllers;

import com.github.ratel.dto.EmailDto;
import com.github.ratel.dto.ForgotPassDto;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.repositories.UserRepository;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.impl.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/forgot")
public class ForgotPasswordController {

    @Value("${app.forgot.password}")
    private String forgotPassword;
    // = "Follow the link for change password http://localhost:8083/forgot/password?code=";

    private final UserRepository userRepository;

    private final ForgotPasswordService passwordService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ForgotPasswordController(UserRepository userRepository,
                                    ForgotPasswordService passwordService,
                                    EmailService emailService,
                                    PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void sendingLetterToReplacementPassword(@RequestBody @Valid EmailDto emailDto) {
        User user = passwordService.findByEmail(emailDto.getEmail());
        if (user.getEmail() != null) {
            user.setActivationCode(UUID.randomUUID().toString());
            emailService.sendMessageToEmail(user.getEmail(), "Password change",
                    forgotPassword + user.getActivationCode());
            userRepository.save(user);
        } else { throw new NullPointerException("Email does not match"); }
    }

    @GetMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestParam ("code") String code) {
        User user = userRepository.findByActivationCode(code);
        if (user.getActivationCode() == null) {
            throw new NullPointerException("ActivationCode to change the password not found");
        }
    }

    @PostMapping("/change")
    @ResponseStatus(HttpStatus.OK)
    public void enterNewPassword(@RequestBody @Valid ForgotPassDto forgotDto) {
        User user = passwordService.findByLogin(forgotDto.getLogin());
        if (user.getLogin().equals(forgotDto.getLogin())) {
            if(forgotDto.getNewPassword().equals(forgotDto.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(forgotDto.getConfirmPassword()));
            userRepository.save(user);
            } else { throw new ConfirmPasswordException("NewPassword does not match confirmPassword");
            }
        } else {
            throw new NullPointerException("There is no user with this login");
        }
    }

}
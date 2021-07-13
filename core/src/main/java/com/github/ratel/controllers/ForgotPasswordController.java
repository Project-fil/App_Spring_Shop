package com.github.ratel.controllers;

import com.github.ratel.dto.ForgotPassDto;
import com.github.ratel.entity.ConfirmToken;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.WrongUserEmail;
import com.github.ratel.services.ConfirmTokenService;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.impl.ForgotPasswordService;
import com.github.ratel.services.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/forgot")
@RequiredArgsConstructor
public class ForgotPasswordController {

    @Value("${app.email.text}")
    private String textMessageEmail;

    @Value("${app.forgot.domain}")
    private String forgotPasswordDomain;

    private final UserService userService;

    private final ForgotPasswordService passwordService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final ConfirmTokenService confirmTokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void submitForgotPassword(@Valid @RequestBody ForgotPassDto payload) {
        User user = this.passwordService.findByEmail(payload.getEmail());
        if (StringUtils.hasText(user.getEmail())) {
            String token = UUID.randomUUID().toString();
            ConfirmToken ct = new ConfirmToken(user, token, this.passwordEncoder.encode(payload.getConfirmPassword()));
            var pattern = String.format(
                    this.textMessageEmail, "change password ",
                    this.forgotPasswordDomain,
                    "/forgot/password?token=", token);
            this.emailService.sendMessageToEmail(
                    user.getEmail(), "Password change", pattern);
            this.confirmTokenService.create(ct);
        } else {
            throw new WrongUserEmail();
        }
    }

    @GetMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestParam("token") String token) {
        ConfirmToken ct = this.confirmTokenService.findByToken(token);
        if (ct.getToken().equals(token)) {
            User user = ct.getUser();
            this.userService.updateUser(user.newPass(ct.getNewPass()));
        } else {
            throw new InvalidTokenException("Invalid forgot token");
        }
    }
}
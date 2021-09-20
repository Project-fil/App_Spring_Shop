package com.github.ratel.services.impl;

import com.github.ratel.dto.ForgotPassDto;
import com.github.ratel.entity.ConfirmToken;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.WrongUserEmail;
import com.github.ratel.repositories.UserRepository;
import com.github.ratel.services.ConfirmTokenService;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl {

    @Value("${app.email.text}")
    private String textMessageEmail;

    @Value("${app.forgot.domain}")
    private String forgotPasswordDomain;

    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmTokenService confirmTokenService;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("Нет электронной почты для смены пароля"));
    }

    public void passwordRecovery(ForgotPassDto payload) {
        User user = this.findByEmail(payload.getEmail());
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

    public void passwordChange(String token) {
        ConfirmToken ct = this.confirmTokenService.findByToken(token);
        if (ct.getToken().equals(token)) {
            User user = ct.getUser();
            this.userService.updateUser(user.newPass(ct.getNewPass()));
        } else {
            throw new InvalidTokenException("Неверный токен");
        }
    }

}

package com.github.ratel.services.impl;

import com.github.ratel.dto.ForgotPassDto;
import com.github.ratel.entity.ConfirmToken;
import com.github.ratel.entity.User;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.WrongUserEmail;
import com.github.ratel.repositories.ConfirmTokenRepository;
import com.github.ratel.repositories.UserRepository;
import com.github.ratel.services.ConfirmTokenService;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl {

//    @Value("${app.email.text}")
//    private String textMessageEmail;

    @Value("${app.forgot.domain}")
    private String forgotPasswordDomain;

    private final UserService userService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmTokenRepository tokenRepository;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Нет электронной почты для смены пароля"));
    }

    public void passwordRecovery(ForgotPassDto payload) throws WrongUserEmail, ConfirmPasswordException {
        User user = this.findByEmail(payload.getEmail());
        this.userService.checkPassAndConfirmPass(payload.getNewPassword(), payload.getConfirmPassword());
        if (StringUtils.hasText(user.getEmail())) {
            String token = UUID.randomUUID().toString();
            ConfirmToken ct = new ConfirmToken(
                    user,
                    token,
                    this.passwordEncoder.encode(payload.getConfirmPassword()));
            var pattern =
                    "Здравсвуйте "
                            + user.getFirstname() + " " + user.getLastname()
                            + ". Перейдите по ссылке для подтверждения замены пароля. "
                            + this.forgotPasswordDomain
                            + "/forgot/password?token="
                            + token;
            this.emailService.sendMessageToEmail(
                    user.getEmail(), "Password change", pattern);
            this.tokenRepository.save(ct);
        } else {
            throw new WrongUserEmail();
        }
    }

    public void passwordChange(String token) throws InvalidTokenException {
        ConfirmToken ct = this.tokenRepository.findByToken(token).orElse(null);
        if (Objects.nonNull(ct) && ct.getStatus().equals(EntityStatus.on)) {
            User user = ct.getUser();
            this.userService.updateUser(user.newPass(ct.getNewPass()));
            ct.setStatus(EntityStatus.off);
            this.tokenRepository.save(ct);
        } else {
            throw new InvalidTokenException("Неверный токен");
        }
    }

}

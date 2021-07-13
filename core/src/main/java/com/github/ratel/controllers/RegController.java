package com.github.ratel.controllers;

import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.UserVerificationStatus;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.VerificationTokenService;
import com.github.ratel.services.impl.UserService;
import com.github.ratel.utils.TransferObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
public class RegController {

    @Value("${app.email.text}")
    private String textMessageEmail;

    @Value("${app.verification.domain}")
    private String textMessageSendEmail;

    private final UserService userService;

    private final VerificationTokenService tokenService;

    private final EmailService emailService;

    @Autowired
    public RegController(UserService userService, VerificationTokenService tokenService, EmailService emailService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody @Valid UserRegDto payload) {
        if (this.userService.findByLogin(payload.getLogin()) != null) {
            throw new UserAlreadyExistException();
        }
        User user = TransferObj.toUser(payload);
        var token = (UUID.randomUUID().toString());
        var pattern = String.format(
                this.textMessageEmail, "verification ",
                this.textMessageSendEmail,
                "/verification?token=", token);
        this.emailService.sendMessageToEmail(user.getEmail(), "Verification user",
                pattern);
        this.userService.saveUser(user);
        this.tokenService.create(new VerificationToken(user, token));
    }

    @GetMapping("/verification")
    @ResponseStatus(HttpStatus.OK)
    public void passingVerification(@RequestParam("token") String token) {
        VerificationToken vt = this.tokenService.findByToken(token);
        if (vt.getToken().equals(token)) {
            User user = vt.getUser();
            this.userService.updateUser(user.verificUser(UserVerificationStatus.VERIFIED));
        } else {
            throw new InvalidTokenException("Invalid verification token");
        }
    }
}

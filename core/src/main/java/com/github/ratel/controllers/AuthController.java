package com.github.ratel.controllers;

import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.AuthResponse;
import com.github.ratel.payload.UserVerificationStatus;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.impl.UserService;
import com.github.ratel.utils.TransferObj;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
public class AuthController {

    @Value("${app.message.email}")
    private String textMessageSendEmail;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    private final EmailService emailService;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider,
                          UserService userService,
                          EmailService emailService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody @Valid UserRegDto payload) {
        if (userService.findByLogin(payload.getLogin()) != null) {
            throw new UserAlreadyExistException("User already exist");
        }
        User user = TransferObj.toUser(payload);
        user.setActivationCode(UUID.randomUUID().toString());
        emailService.sendMessageToEmail(user.getEmail(), "Verification user",
                textMessageSendEmail + user.getActivationCode());
        userService.saveUser(user);
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

    @GetMapping("/authorization/verification")
    public String passingVerification(Model model, @RequestParam("code") String code) {
        boolean isActivateUser = userService.verificationUser(code);
        if (isActivateUser) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }
        return "<h1>Verification correct</h1>";
    }
}

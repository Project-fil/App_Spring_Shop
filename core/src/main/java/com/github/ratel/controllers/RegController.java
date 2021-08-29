package com.github.ratel.controllers;

import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.services.EmailService;
import com.github.ratel.services.VerificationTokenService;
import com.github.ratel.services.impl.UserService;
import com.github.ratel.utils.TransferObj;
import com.github.ratel.utils.UserTransferObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> registrationAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
       try {
           this.userService.saveAdmin(createAdminRequest);
           return ResponseEntity.ok("Admin register successfully");
       } catch (UserAlreadyExistException e) {
           return ResponseEntity.status(422).body(e.getMessage());
       }
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

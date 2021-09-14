package com.github.ratel.controllers;

import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.services.VerificationTokenService;
import com.github.ratel.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
public class RegController {

    private final UserServiceImpl userServiceImpl;

    private final VerificationTokenService verificationTokenService;


    @Autowired
    public RegController(UserServiceImpl userServiceImpl, VerificationTokenService verificationTokenService) {
        this.userServiceImpl = userServiceImpl;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping("/create/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> registrationAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
       try {
           this.userServiceImpl.saveAdmin(createAdminRequest);
           return ResponseEntity.ok("Admin register successfully");
       } catch (UserAlreadyExistException e) {
           return ResponseEntity.status(422).body(e.getMessage());
       }
    }

    @GetMapping("/verification")
    @ResponseStatus(HttpStatus.OK)
    public String passingVerification(@RequestParam("token") String token) {
        VerificationToken vt = this.verificationTokenService.findByToken(token);
        if (vt.getToken().equals(token)) {
            User user = vt.getUser();
            this.userServiceImpl.updateUser(user.verificationUser(UserVerificationStatus.VERIFIED));
        } else {
            throw new InvalidTokenException("Invalid verification token");
        }
        return "Верификация прошла успешно";
    }
}

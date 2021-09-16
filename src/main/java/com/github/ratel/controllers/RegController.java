package com.github.ratel.controllers;

import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.services.UserService;
import com.github.ratel.services.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegController {

    private final UserService userService;

    private final VerificationTokenService verificationTokenService;


//    @Autowired
//    public RegController(UserServiceImpl userServiceImpl, VerificationTokenService verificationTokenService) {
//        this.userServiceImpl = userServiceImpl;
//        this.verificationTokenService = verificationTokenService;
//    }

    @PostMapping("/create/admin")
    public ResponseEntity<Object> registrationAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        try {
            this.userService.saveAdmin(createAdminRequest);
            return ResponseEntity.ok("Администратор успешно зарегистрирован");
        } catch (ConfirmPasswordException | UserAlreadyExistException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    @GetMapping("/verification")
    public ResponseEntity<Object> passingVerification(@RequestParam("token") String token) {
        try {
            this.verificationTokenService.verificationUser(token);
            return ResponseEntity.ok("Верификация прошла успешно");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Неверный токен подтверждения");
        }
    }
}

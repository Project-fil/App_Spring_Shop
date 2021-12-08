package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.UnverifiedException;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.UserAuthRequest;
import com.github.ratel.payload.response.TokenResponse;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.security.UserDetailsImpl;
import com.github.ratel.services.UserService;
import com.github.ratel.services.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/app/shop")
@RequiredArgsConstructor
public class RegController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final VerificationTokenService verificationTokenService;

    @PostMapping("/create/admin")
    public ResponseEntity<Object> registrationAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        try {
            this.userService.saveAdmin(createAdminRequest);
            return ResponseEntity.ok("Администратор успешно зарегистрирован");
        } catch (ConfirmPasswordException | UserAlreadyExistException e) {
            return ResponseEntity.status(422).body(e.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registrationManager(@RequestBody @Valid UserDto payload) {
        try {
            return ResponseEntity.ok(this.userService.createUser(payload));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/verification")
    public ResponseEntity<Object> passingVerification(@RequestParam("token") String token) {
        try {
            VerificationToken vt = verificationTokenService.findByToken(token);
            User user = vt.getUser();
            this.userService.updateUser(user.verificationUser(UserVerificationStatus.VERIFIED));
            return ResponseEntity.ok("Верификация прошла успешно");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Неверный токен подтверждения");
        }
    }

    @PostMapping("/authorization")
    public ResponseEntity<Object> auth(@RequestBody @Valid UserAuthRequest userAuthRequest) {
        User user = this.userService.findByEmailAndPassword(userAuthRequest.getEmail(), userAuthRequest.getPassword());
        if (user.getVerification().equals(UserVerificationStatus.UNVERIFIED)) {
            throw new UnverifiedException("Пользователь не верефицирован");
        } else {
            return ResponseEntity.ok(new TokenResponse(this.tokenProvider.generateToken(UserDetailsImpl.fromUserToCustomUserDetails(user))));
        }
    }

}

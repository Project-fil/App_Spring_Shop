package com.github.ratel.controllers.impl;

import com.github.ratel.dto.ForgotPassDto;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.InvalidTokenException;
import com.github.ratel.exceptions.WrongUserEmail;
import com.github.ratel.services.impl.ForgotPasswordServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("app/shop/")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordServiceImpl passwordService;

    @PostMapping("free/forgot")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> submitForgotPassword(@Valid @RequestBody ForgotPassDto payload) {
        this.passwordService.passwordRecovery(payload);
        return ResponseEntity.ok("Для подтверждения воспользуйтесь електронной почтой");
    }

    @GetMapping("free/password")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> changePassword(@RequestParam("token") String token) {
        this.passwordService.passwordChange(token);
        return ResponseEntity.status(200).body("Изменение пароля прошло успешно");
    }
}
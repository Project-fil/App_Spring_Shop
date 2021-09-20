package com.github.ratel.controllers;

import com.github.ratel.dto.ForgotPassDto;
import com.github.ratel.services.impl.ForgotPasswordServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/forgot")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final ForgotPasswordServiceImpl passwordService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void submitForgotPassword(@Valid @RequestBody ForgotPassDto payload) {
        this.passwordService.passwordRecovery(payload);
    }

    @GetMapping("/password")
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestParam("token") String token) {
        this.passwordService.passwordChange(token);
    }

}
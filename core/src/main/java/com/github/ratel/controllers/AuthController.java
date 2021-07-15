package com.github.ratel.controllers;

import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.NotVerificationException;
import com.github.ratel.payload.AuthResponse;
import com.github.ratel.payload.UserVerificationStatus;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.services.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider,
                          UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/authorization")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse auth(@RequestBody @Valid UserAuthDto userAuthDto) {
        User user = this.userService.findByLoginAndPassword(userAuthDto.getLogin(), userAuthDto.getPassword());
        String token = null;
        if (user.getVerification().equals(UserVerificationStatus.UNVERIFIED)) {
            throw new NotVerificationException();
        } else if (user.getVerification().equals(UserVerificationStatus.VERIFIED)) {
            token = this.tokenProvider.generateToken(user.getLogin());
//            token.concat("Bearer ");
        }
        return new AuthResponse(token);
    }
}

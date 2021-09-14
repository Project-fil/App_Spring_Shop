package com.github.ratel.controllers;

import com.github.ratel.entity.User;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.NotVerificationException;
import com.github.ratel.payload.request.UserAuthRequest;
import com.github.ratel.payload.response.TokenResponse;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.services.impl.UserServiceImpl;
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

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider,
                          UserServiceImpl userServiceImpl) {
        this.tokenProvider = tokenProvider;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("/authorization")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponse auth(@RequestBody @Valid UserAuthRequest userAuthRequest) {
        User user = this.userServiceImpl.findByEmailAndPassword(userAuthRequest.getEmail(), userAuthRequest.getPassword());
        String token = null;
        if (user.getVerification().equals(UserVerificationStatus.UNVERIFIED)) {
            throw new NotVerificationException();
        } else if (user.getVerification().equals(UserVerificationStatus.VERIFIED)) {
            token = this.tokenProvider.generateToken(user.getEmail());
        }
        return new TokenResponse(token);
    }
}

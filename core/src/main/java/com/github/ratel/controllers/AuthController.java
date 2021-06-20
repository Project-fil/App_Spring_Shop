package com.github.ratel.controllers;

import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.exception.UserAlreadyExistException;
import com.github.ratel.payload.UserVerificationStatus;
import com.github.ratel.security.AuthResponse;
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

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public HttpStatus registration(@RequestBody @Valid UserRegDto payload) {
        if (userService.findByLogin(payload.getLogin()) != null) {
            throw new UserAlreadyExistException("User already exist");
        }
        User user = new User();
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.setEmail(payload.getEmail());
        user.setLogin(payload.getLogin());
        user.setPassword(payload.getPassword());
        user.setPhone(payload.getPhone());
        user.setAddress(payload.getAddress());
        user.setCreatedAt(payload.getCreatedAt());
        user.setVerification(UserVerificationStatus.UNVERIFIED);
        userService.saveUser(user);
        return HttpStatus.OK;
    }

    @PostMapping("/authorization")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse auth(@RequestBody @Valid UserAuthDto userAuthDto) {
        User user = userService.findByLoginAndPassword(userAuthDto.getLogin(), userAuthDto.getPassword());
        String token = null;
//        if(user.getVerification().equals(UserVerificationStatus.UNVERIFIED)){
//            log.error("You are not verified");
//        } else if(user.getVerification().equals(UserVerificationStatus.VERIFIED)) {
        token = tokenProvider.generateToken(user.getLogin());
//        }
        return new AuthResponse(token);
    }
}

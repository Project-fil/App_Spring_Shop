package com.github.ratel.controllers;

import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.exception.IncorrectUserFieldsException;
import com.github.ratel.security.AuthResponse;
import com.github.ratel.security.JwtTokenProvider;
import com.github.ratel.services.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider, UserService userService) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registration(@Valid @RequestBody UserRegDto userRegDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new IncorrectUserFieldsException(errorMessage);
        }
        User user = new User();
        user.setHashPassword(userRegDto.getHashPassword());
        user.setLogin(userRegDto.getLogin());
        userService.saveUser(user);
        return "Ok";
    }

    @PostMapping("/authorization")
    public AuthResponse auth(@Valid @RequestBody UserAuthDto userAuthDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            log.error(errorMessage);
            throw new IncorrectUserFieldsException(errorMessage);
        }
        User user = userService.findByLoginAndPassword(userAuthDto.getLogin(), userAuthDto.getPassword());
        String token = tokenProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}

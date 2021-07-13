package com.github.ratel.services;

import com.github.ratel.entity.ConfirmToken;

public interface ConfirmTokenService {

    ConfirmToken create(ConfirmToken crt);

    ConfirmToken findByToken(String token);

    void delete(Long id);

}

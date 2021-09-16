package com.github.ratel.services;

import com.github.ratel.entity.VerificationToken;

public interface VerificationTokenService {

    void verificationUser(String token);

    VerificationToken create(VerificationToken crt);

    VerificationToken findByToken(String token);

}

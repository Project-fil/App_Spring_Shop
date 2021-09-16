package com.github.ratel.services.impl;

import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.repositories.VerificationTokenRepository;
import com.github.ratel.services.UserService;
import com.github.ratel.services.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    @Override
    public VerificationToken create(VerificationToken token) {
        return this.tokenRepository.save(token);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return this.tokenRepository.findByToken(token)
                .orElseThrow( () -> new EntityNotFoundException("Обьект не найден"));
    }

}

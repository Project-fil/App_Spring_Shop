package com.github.ratel.services.impl;

import com.github.ratel.entity.ConfirmToken;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.ConfirmTokenRepository;
import com.github.ratel.services.ConfirmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmTokenServiceImpl implements ConfirmTokenService {

    private final ConfirmTokenRepository tokenRepository;

    @Override
    public ConfirmToken create(ConfirmToken crt) {
        return this.tokenRepository.save(crt);
    }

    @Override
    public ConfirmToken findByToken(String token) {
        return this.tokenRepository.findByToken(token).orElseThrow(EntityNotFound::new);
    }

    @Override
    public void delete(Long id) {
        this.tokenRepository.update(id, EntityStatus.off);
    }
}

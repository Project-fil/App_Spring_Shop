package com.github.ratel.services.impl;

import com.github.ratel.entity.VerificationToken;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.repositories.VerificationTokenRepository;
import com.github.ratel.services.VerificationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    @Override
    public VerificationToken create(VerificationToken crt) {
        return this.tokenRepository.save(crt);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return this.tokenRepository.findByToken(token).orElseThrow( () -> new EntityNotFound("Entity not found in db"));
    }

    @Override
    public void delete(Long id) {
        this.tokenRepository.update(id, EntityStatus.off);
    }
}

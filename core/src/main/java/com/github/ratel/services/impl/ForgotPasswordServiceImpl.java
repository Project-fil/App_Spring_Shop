package com.github.ratel.services.impl;

import com.github.ratel.entity.User;
import com.github.ratel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public ForgotPasswordServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("No email to change password"));
    }

//    public User findByLogin (String login) { return userRepository.findByLogin(login); }
}

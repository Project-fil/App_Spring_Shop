package com.github.ratel.services.impl;

import com.github.ratel.repositories.RoleEntityRepo;
import com.github.ratel.repositories.impl.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

@Autowired
    private UserRepository userRepository;
@Autowired
    private RoleEntityRepo roleEntityRepo;
@Autowired
    private PasswordEncoder passwordEncoder;




}

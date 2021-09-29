package com.github.ratel.services.impl;

import com.github.ratel.entity.User;
import com.github.ratel.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserServiceImpl userServiceImpl;

    public CustomUserDetailsService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceImpl.findUserByEmail(username);
        return UserDetailsImpl.fromUserToCustomUserDetails(user);
    }
}

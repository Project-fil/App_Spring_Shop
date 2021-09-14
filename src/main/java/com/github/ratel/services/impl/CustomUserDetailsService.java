package com.github.ratel.services.impl;

import com.github.ratel.entity.User;
import com.github.ratel.security.CustomUserDetails;
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
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userServiceImpl.findUserByEmail(username);
        return CustomUserDetails.fromUserToCustomUserDetails(user);
    }
}

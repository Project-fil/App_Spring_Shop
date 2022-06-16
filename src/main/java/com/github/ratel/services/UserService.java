package com.github.ratel.services;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.payload.response.UserResponse;

import java.security.Principal;
import java.util.List;

public interface UserService {

    User getCurrentUser(Principal principal);

    List<User> findAllUsers();

    User findById(Long id);

    User findUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User save(User user);

    User updateUser(User user);

    void deleteUserById(Long userId);

    User checkUserByEmail(String email);
}

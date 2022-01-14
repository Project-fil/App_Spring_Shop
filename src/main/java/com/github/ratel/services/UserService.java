package com.github.ratel.services;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.payload.response.UserResponse;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findById(String id);

    User findUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User save(User user);

    void updateUser(User user);

    void deleteUserById(String userId);

    void checkUserByEmail(String email);

    String checkPassAndConfirmPass(String pass, String confirmPass);

}

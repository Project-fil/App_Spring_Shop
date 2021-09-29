package com.github.ratel.services;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.payload.request.CreateAdminRequest;
import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.payload.response.UserResponse;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findById(long id);

    User findUserById(long userId);

    User findUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    void saveAdmin(CreateAdminRequest payload);

    UserResponse createUser(UserDto payload);

    void createManager(ManagerRequest payload);

    void updateUser(User user);

//    User changeUserInfo(long userId, UserRegDto userRegDto);

    String checkPassAndConfirmPass(String pass, String confirmPass);

    void deleteUserById(long userId);


}

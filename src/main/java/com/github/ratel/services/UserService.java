package com.github.ratel.services;

import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.entity.VerificationToken;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.UserVerificationStatus;
import com.github.ratel.exceptions.ConfirmPasswordException;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.exceptions.UserAlreadyExistException;
import com.github.ratel.payload.request.CreateAdminRequest;

import java.util.*;

public interface UserService {

    List<User> findAllUsers();

    User findById(long id);

    User findUserById(long userId);

    User findUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User saveUser(User user);

    void saveAdmin(CreateAdminRequest payload);

    void updateUser(User user);

//    long createUser(UserRegDto userRegDto);

//    User changeUserInfo(long userId, UserRegDto userRegDto);

     void deleteUserById(long userId);


}

package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.services.impl.UserService;
import com.github.ratel.utils.TransferObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto findUserById(@PathVariable long userId) {
        Optional<User> user = userService.findById(userId);
            return TransferObj.toDto(user);
    }

    @PostMapping
    public long createUser(@RequestBody UserRegDto userRegDto) {
        return userService.createUser(userRegDto);
    }

    @PutMapping("/{userId}")
    public User changeUserInfo(@PathVariable long userId, @RequestBody UserRegDto userRegDto) {
        return userService.changeUserInfo(userId, userRegDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        this.userService.deleteUserById(userId);
    }
}

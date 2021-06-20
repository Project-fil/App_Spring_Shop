package com.github.ratel.controllers;

import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.services.impl.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable long userId) {
        return userService.findUserById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));
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
        userService.deleteUser(userId);
    }
}

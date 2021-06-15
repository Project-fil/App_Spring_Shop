package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.services.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registration")
    public void registration() {
        // TODO: delegate to spring security
    }

    @PostMapping("/authorization")
    public void authorization() {
        // TODO: delegate to spring security
    }

    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable long userId) {
        return userService.findUserById(userId).orElseThrow(() -> new RuntimeException("Not found user!"));
    }

    @PostMapping
    public long createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{userId}")
    public User changeUserInfo(@PathVariable long userId, @RequestBody UserDto userDto) {
        return userService.changeUserInfo(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
    }
}

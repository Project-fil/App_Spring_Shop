package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.User;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.services.impl.UserService;
import com.github.ratel.utils.TransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Secured("ROLE_USER")
    @GetMapping
    public List<UserDto> findAllActiveUsers() {
        List<User> users = this.userService.findAllUsers();
        return TransferObj.toAllDto(users).stream()
                .filter(user -> user.getStatus().equals(EntityStatus.on))
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    public UserDto findUserById(@PathVariable long userId) {
        User user = userService.findById(userId);
            return TransferObj.toDto(user);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public long createUser(@RequestBody UserRegDto userRegDto) {
        return userService.createUser(userRegDto);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{userId}")
    public User changeUserInfo(@PathVariable long userId, @RequestBody UserRegDto userRegDto) {
        return userService.changeUserInfo(userId, userRegDto);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        this.userService.deleteUserById(userId);
    }
}

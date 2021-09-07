package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.services.impl.UserServiceImpl;
import com.github.ratel.utils.UserTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Secured("ROLE_USER")
    @GetMapping
    public List<UserDto> findAllActiveUsers() {
        List<User> users = this.userServiceImpl.findAllUsers();
        return UserTransferObject.toAllDto(users).stream()
                .filter(user -> user.getStatus().equals(EntityStatus.on))
                .collect(Collectors.toList());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    public UserDto findUserById(@PathVariable long userId) {
        User user = userServiceImpl.findById(userId);
            return UserTransferObject.toDto(user);
    }

//    @Secured("ROLE_ADMIN")
//    @PostMapping
//    public long createUser(@RequestBody UserRegDto userRegDto) {
//        return userService.createUser(userRegDto);
//    }

//    @Secured("ROLE_ADMIN")
//    @PutMapping("/{userId}")
//    public User changeUserInfo(@PathVariable long userId, @RequestBody UserRegDto userRegDto) {
//        return userService.changeUserInfo(userId, userRegDto);
//    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable long userId) {
        this.userServiceImpl.deleteUserById(userId);
    }
}

package com.github.ratel.controllers;

import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.services.impl.UserServiceImpl;
import com.github.ratel.utils.transfer_object.UserTransferObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController implements ApiSecurityHeader{

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    @SecurityRequirement(name = "Authorization")
    public List<UserDto> findAllActiveUsers() {
        List<User> users = this.userServiceImpl.findAllUsers().stream()
                .filter(user -> user.getStatus().equals(EntityStatus.on))
                .collect(Collectors.toList());
        return UserTransferObject.toAllDto(users);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    @SecurityRequirement(name = "Authorization")
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
    @SecurityRequirement(name = "Authorization")
    public void deleteUser(@PathVariable long userId) {
        this.userServiceImpl.deleteUserById(userId);
    }
}

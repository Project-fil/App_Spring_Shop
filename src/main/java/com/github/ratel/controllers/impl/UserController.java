package com.github.ratel.controllers.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.dto.UserDto;
import com.github.ratel.entity.User;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.services.UserService;
import com.github.ratel.services.impl.UserServiceImpl;
import com.github.ratel.utils.transfer_object.UserTransferObject;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/shop/")
@SecurityRequirement(name = "Authorization")
@RequiredArgsConstructor
public class UserController implements ApiSecurityHeader {

    private final UserService userService;

    @GetMapping("user")
    public UserResponse getCurrentUser(Principal principal) {
        User user = this.userService.getCurrentUser(principal);
        return UserTransferObject.fromUser(user);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("user/all")
    public ResponseEntity<List<UserResponse>> findAllActiveUsers() {
        return ResponseEntity.ok(List.of());
    }

//    @Secured("ROLE_ADMIN")
//    @GetMapping("/{userId}")
//    @SecurityRequirement(name = "Authorization")
//    public UserDto findUserById(@PathVariable String userId) {
//        User user = userServiceImpl.findById(userId);
//            return UserTransferObject.toDto(user);
//    }

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
    public void deleteUser(@PathVariable String userId) {
        this.userService.deleteUserById(userId);
    }
}

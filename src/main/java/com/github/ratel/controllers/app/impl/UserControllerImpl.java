package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.app.interfaces.UserController;
import com.github.ratel.entity.User;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.services.UserService;
import com.github.ratel.utils.transfer_object.UserTransferObj;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/app/shop/")
@RequiredArgsConstructor
public class UserControllerImpl implements ApiSecurityHeader, UserController {

    private final UserService userService;

    public UserResponse getCurrentUser(Principal principal) {
        User user = this.userService.getCurrentUser(principal);
        return UserTransferObj.fromUser(user);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{userId}")
    @SecurityRequirement(name = "Authorization")
    public UserResponse findUserById(@PathVariable Long userId) {
        User user = this.userService.findById(userId);

        return null;
    }

    @Secured("ROLE_ADMIN")

    @SecurityRequirement(name = "Authorization")
    public void deleteUser(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);
    }
}

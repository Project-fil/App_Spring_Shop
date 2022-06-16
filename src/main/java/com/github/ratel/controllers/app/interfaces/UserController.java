package com.github.ratel.controllers.app.interfaces;

import com.github.ratel.payload.response.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@SecurityRequirement(name = "Authorization")
public interface UserController {

    @GetMapping("user")
    UserResponse getCurrentUser(Principal principal);

    UserResponse findUserById(@PathVariable Long userId);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Long userId);

}

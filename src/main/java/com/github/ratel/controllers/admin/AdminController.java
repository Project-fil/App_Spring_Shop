package com.github.ratel.controllers.admin;

import com.github.ratel.payload.response.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@SecurityRequirement(name = "Authorization")
public interface AdminController {

    @GetMapping("user/all")
    ResponseEntity<List<UserResponse>> findAllActiveUsers();

    @GetMapping("/{userId}")
    ResponseEntity<UserResponse> findUserById(@PathVariable Long userId);

    @DeleteMapping("/{userId}")
    ResponseEntity<Object> deleteUser(@PathVariable Long userId);


}

package com.github.ratel.controllers.app.interfaces;

import com.github.ratel.payload.request.UserUpdateRequest;
import com.github.ratel.payload.response.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@SecurityRequirement(name = "Authorization")
public interface UserController {

    @GetMapping("user")
    ResponseEntity<UserResponse> getCurrentUser(Principal principal);

    @PutMapping(value = "user/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserResponse> update(
            @Valid @RequestPart("body") UserUpdateRequest updateRequest,
            @RequestPart(value = "image", required = false) MultipartFile image
            );

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable Long userId);

}

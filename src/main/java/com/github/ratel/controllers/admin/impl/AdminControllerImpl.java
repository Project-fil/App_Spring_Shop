package com.github.ratel.controllers.admin.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.admin.AdminController;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.services.UserService;
import com.github.ratel.utils.transfer_object.UserTransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/")
public class AdminControllerImpl implements ApiSecurityHeader, AdminController {

    private final UserService userService;

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserResponse>> findAllActiveUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers().stream()
                .map(UserTransferObj::fromUser)
                .collect(Collectors.toList())
        );
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserResponse> findUserById(Long userId) {
        return ResponseEntity.ok(UserTransferObj.fromUser(this.userService.findById(userId)));
    }

    @Override
    public ResponseEntity<Object> deleteUser(Long userId) {
        String userName = this.userService.deleteUserById(userId);
        return ResponseEntity.ok("User " + userName + " deleted!");
    }

}

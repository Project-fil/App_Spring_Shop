package com.github.ratel.controllers.admin.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.entity.User;
import com.github.ratel.entity.enums.Roles;
import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.payload.response.UserResponse;
import com.github.ratel.services.UserService;
import com.github.ratel.utils.transfer_object.UserTransferObj;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/")
public class AdminControllerImpl implements ApiSecurityHeader {

    private final UserService userService;

    @Secured("ROLE_ADMIN")

    public ResponseEntity<Object> createManager(@RequestBody ManagerRequest payload) {
        try {
            User user = this.userService.findById(payload.getUserId());
            user.setRoles(Roles.ROLE_MANAGER);
            this.userService.save(user);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok("Success");
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("user/all")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<List<UserResponse>> findAllActiveUsers() {
        return ResponseEntity.ok(this.userService.findAllUsers().stream()
                .map(UserTransferObj::fromUser)
                .collect(Collectors.toList())
        );
    }

}

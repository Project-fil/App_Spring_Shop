package com.github.ratel.controllers;

import com.github.ratel.payload.request.ManagerRequest;
import com.github.ratel.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("admin/")
public class AdminController {

    private final UserService userService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/create/manager")
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<Object> createManager(@RequestBody ManagerRequest payload) {
        try {
            this.userService.createManager(payload);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.ok("Success");
    }
}

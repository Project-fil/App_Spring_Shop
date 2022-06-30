package com.github.ratel.controllers.app.interfaces;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@SecurityRequirement(name = "Authorization")
public interface BrandControllerApp {

    @GetMapping("brand/all")
    List<ResponseEntity<Object>> getAll();

    @GetMapping("brand/get/{id}")
    ResponseEntity<Object> getById(@PathVariable Long id);

}

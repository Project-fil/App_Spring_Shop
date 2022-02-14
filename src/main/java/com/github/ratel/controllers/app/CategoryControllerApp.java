package com.github.ratel.controllers.app;

import com.github.ratel.payload.response.CategoryResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SecurityRequirement(name = "Authorization")
public interface CategoryControllerApp {

    @GetMapping("free/category/all")
    ResponseEntity<List<CategoryResponse>> readAllCategory();

    @GetMapping("free/category/id")
    ResponseEntity<CategoryResponse> getById(@RequestParam Long id);

    @GetMapping("free/category/name")
    ResponseEntity<CategoryResponse> getByName(@RequestParam String name);

}

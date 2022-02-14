package com.github.ratel.controllers.admin;

import com.github.ratel.payload.request.CategoryRequest;
import com.github.ratel.payload.response.CategoryResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "Authorization")
public interface CategoryControllerAdmin {


    @GetMapping("category/all")
    ResponseEntity<List<CategoryResponse>> findAllCategory();

    @GetMapping("category/id")
    ResponseEntity<CategoryResponse> findById(@RequestParam Long id);

    @GetMapping("category/name")
    ResponseEntity<CategoryResponse> findByName(@RequestParam String name);

    @PostMapping("category/create")
    ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest);

    @PutMapping("category/update")
    ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @RequestParam long id);

//    @DeleteMapping("category/delete")
//    void deleteCategory (@RequestParam long id);

}

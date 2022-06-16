package com.github.ratel.controllers.admin.interf;

import com.github.ratel.payload.request.CategoryRequest;
import com.github.ratel.payload.response.CategoryResponse;
import com.github.ratel.payload.response.MessageResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Authorization")
public interface CategoryControllerAdmin {

    @PostMapping("category/create")
    ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest);

    @PutMapping("category/update")
    ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @RequestParam long id);

    @DeleteMapping("category/delete")
    ResponseEntity<MessageResponse> deleteCategory (@RequestParam long id);

}

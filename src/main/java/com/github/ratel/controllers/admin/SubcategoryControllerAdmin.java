package com.github.ratel.controllers.admin;

import com.github.ratel.payload.request.SubcategoryRequest;
import com.github.ratel.payload.response.MessageResponse;
import com.github.ratel.payload.response.SubcategoryResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "Authorization")
public interface SubcategoryControllerAdmin {

    @PostMapping("subcategory/create")
    ResponseEntity<SubcategoryResponse> createCategory(@RequestBody SubcategoryRequest subcategoryRequest);

    @PutMapping("subcategory/update")
    ResponseEntity<SubcategoryResponse> updateCategory(@RequestBody SubcategoryRequest subcategoryRequest, @RequestParam long id);

    @DeleteMapping("subcategory/delete")
    ResponseEntity<MessageResponse> deleteSubcategory (@RequestParam long id);

}

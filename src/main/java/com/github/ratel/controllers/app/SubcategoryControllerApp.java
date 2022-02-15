package com.github.ratel.controllers.app;

import com.github.ratel.payload.response.SubcategoryResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@SecurityRequirement(name = "Authorization")
public interface SubcategoryControllerApp {

    @GetMapping("free/subcategory/all")
    ResponseEntity<List<SubcategoryResponse>> readAll(@RequestParam long categoryId);

    @GetMapping("free/subcategory/id")
    ResponseEntity<SubcategoryResponse> getById(@RequestParam long id);

    @GetMapping("free/subcategory/name")
    ResponseEntity<SubcategoryResponse> getByName(@RequestParam String name);


}

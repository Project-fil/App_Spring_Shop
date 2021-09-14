package com.github.ratel.controllers;

import com.github.ratel.dto.SubcategoryDto;
import com.github.ratel.entity.Category;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.services.CategoryService;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.utils.TransferObj;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryController implements ApiSecurityHeader{

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(CategoryService categoryService, SubcategoryService subcategoryService) {
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/status")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public List<SubcategoryDto> readAllSubcategoryByStatus(@PathVariable EntityStatus status) {
        List<Subcategory> subcategories = this.subcategoryService.findAllSubcategoryByStatus(status);
        return TransferObj.toAllSubcategoryDto(subcategories);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping()
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public List<SubcategoryDto> readAllSubcategory() {
        List<Subcategory> subcategories = this.subcategoryService.findByAllSubcategory();
        return TransferObj.toAllSubcategoryDto(subcategories);
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public List<SubcategoryDto> readAllSubcategoryForUser() {
        List<Subcategory> subcategories = this.subcategoryService.findByAllSubcategory().stream()
                .filter(subcategory -> subcategory.getStatus().equals(EntityStatus.on))
                .collect(Collectors.toList());
        return TransferObj.toAllSubcategoryDto(subcategories);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{id}")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public SubcategoryDto readById(@PathVariable long id) {
       Subcategory subcategory = this.subcategoryService.findById(id);
       return TransferObj.toSubcategory(subcategory);
    }

    @Secured("ROLE_USER")
    @GetMapping("/name/{name}")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public SubcategoryDto readByName(@PathVariable String name) {
        Subcategory subcategory = this.subcategoryService.findByName(name);
        return TransferObj.toSubcategory(subcategory);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{categoryId}")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSubcategory(@PathVariable Long categoryId, @RequestBody SubcategoryDto payload) {
        Category c = this.categoryService.raedById(categoryId);
        Subcategory subcategory = TransferObj.toSubcategoryFromUser(payload);
        subcategory.setCategory(c);
        c.addSubcategory(subcategory);
        this.subcategoryService.create(subcategory);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public void updateSubcategory(@RequestBody SubcategoryDto payload) {
        Subcategory subcategory = TransferObj.toSubcategoryFromUser(payload);
        this.subcategoryService.update(subcategory);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "Authorization")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubcategory(@PathVariable long id) {
        this.subcategoryService.delete(id);
    }

}

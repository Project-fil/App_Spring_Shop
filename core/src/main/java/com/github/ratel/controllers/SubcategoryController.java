package com.github.ratel.controllers;

import com.github.ratel.dto.CategoryDto;
import com.github.ratel.dto.SubcategoryDto;
import com.github.ratel.entity.Category;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.services.CategoryService;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.utils.TransferObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryController {

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(CategoryService categoryService, SubcategoryService subcategoryService) {
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubcategoryDto> readAllSubcategory() {
        List<Subcategory> subcategories = this.subcategoryService.findByAllSubcategory();
        return TransferObj.toAllSubcategoryDto(subcategories);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubcategoryDto readById(@PathVariable long id) {
        return this.subcategoryService.findById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public SubcategoryDto readByName(@PathVariable String name) {
        return this.subcategoryService.findByName(name);
    }

    @PostMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Subcategory saveSubcategory(@PathVariable Long categoryId, @RequestBody SubcategoryDto subcategoryDto) {
        Category c = this.categoryService.raedById(categoryId);
        Subcategory subcategory = TransferObj.toSubcategoryFromUser(subcategoryDto);
        subcategory.setCategory(c);
        c.addSubcategory(subcategory);
        return this.subcategoryService.create(subcategory);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Subcategory updateSubcategory(@RequestBody Subcategory subcategory) {
        return this.subcategoryService.update(subcategory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSubcategory(@PathVariable long id) {
        this.subcategoryService.delete(id);
    }

}

package com.github.ratel.controllers;

import com.github.ratel.dto.CategoryDto;
import com.github.ratel.entity.Category;
import com.github.ratel.services.CategoryService;
import com.github.ratel.utils.TransferObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@ApiImplicitParams(
        @ApiImplicitParam(
                name = "Authorization",
                value = "Access Token",
                required = true,
                paramType = "header",
                example = "Bearer access_token"
        )
)
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> readAllCategory() {
        return this.categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto readById(@PathVariable long id) {
        return this.categoryService.findCategoryById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto readByName(@PathVariable String name) {
        return this.categoryService.findCategoryByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category saveCategory(@RequestBody CategoryDto categoryDto) {
        Category category = TransferObj.toCategoryFromUser(categoryDto);
        return this.categoryService.createCategory(category);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory (@PathVariable long id) {
        this.categoryService.deleteCategoryById(id);
    }
}

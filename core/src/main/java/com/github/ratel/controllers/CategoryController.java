package com.github.ratel.controllers;

import com.github.ratel.dto.CategoryDto;
import com.github.ratel.entity.Category;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.services.CategoryService;
import com.github.ratel.utils.TransferObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> readAllCategory() {
        List<Category> categories = this.categoryService.findAllCategory();
        return TransferObj.toAllCategoryDto(categories);
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDto> readAllCategoryToUser() {
        List<Category> categories = this.categoryService.findAllCategory();
        return TransferObj.toAllCategoryDto(categories);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto readById(@PathVariable long id) {
        Category category = this.categoryService.raedById(id);
        if (category.getStatus().equals(EntityStatus.on)) {
            return TransferObj.toCategory(category);
        } else {
            throw new EntityNotFound("Category does not exist");
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto readByName(@PathVariable String name) {
        Category category = this.categoryService.findCategoryByName(name);
        if (category.getStatus().equals(EntityStatus.on)) {
            return TransferObj.toCategory(category);
        } else {
            throw new EntityNotFound("Category does not exist");
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody CategoryDto categoryDto) {
        Category category = TransferObj.toCategoryFromUser(categoryDto);
        this.categoryService.createCategory(category);
    }

    @PutMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public void updateCategory(@RequestBody Category category) {
        this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategory (@PathVariable long id) {
        this.categoryService.deleteCategoryById(id);
    }
}

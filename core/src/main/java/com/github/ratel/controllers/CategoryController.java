package com.github.ratel.controllers;

import com.github.ratel.entity.Category;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.request.CategoryRequest;
import com.github.ratel.services.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.ratel.utils.transfer_object.CategoryTransferObj.*;

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
    public List<CategoryRequest> readAllCategory() {
        List<Category> categories = this.categoryService.findAllCategory();
        return toAllCategoryDto(categories);
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryRequest> readAllCategoryToUser() {
        List<Category> categories = this.categoryService.findAllCategory();
        return toAllCategoryDto(categories);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Object> readById(@PathVariable long id) {
        try {
           return ResponseEntity.ok(toCategoryResp(this.categoryService.raedById(id)));
        } catch (EntityNotFound e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryRequest readByName(@PathVariable String name) {
        Category category = this.categoryService.findCategoryByName(name);
        if (category.getStatus().equals(EntityStatus.on)) {
            return toCategory(category);
        } else {
            throw new EntityNotFound("Category does not exist");
        }
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(this.categoryService.createCategory(categoryRequest));
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

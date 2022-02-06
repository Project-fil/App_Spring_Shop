//package com.github.ratel.controllers.impl;
//
//import com.github.ratel.controllers.ApiSecurityHeader;
//import com.github.ratel.entity.Category;
//import com.github.ratel.entity.enums.EntityStatus;
//import com.github.ratel.payload.request.CategoryRequest;
//import com.github.ratel.services.CategoryService;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.web.bind.annotation.*;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//import static com.github.ratel.utils.transfer_object.CategoryTransferObj.*;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/category")
//public class CategoryController implements ApiSecurityHeader {
//
//    private final CategoryService categoryService;
//
//    @Autowired
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @Secured("ROLE_ADMIN")
//    @GetMapping
//    @SecurityRequirement(name = "Authorization")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CategoryRequest> readAllCategory() {
//        List<Category> categories = this.categoryService.findAllCategory();
//        return toAllCategoryDto(categories);
//    }
//
//    @Secured("ROLE_USER")
//    @GetMapping("/user")
//    @SecurityRequirement(name = "Authorization")
//    @ResponseStatus(HttpStatus.OK)
//    public List<CategoryRequest> readAllCategoryToUser() {
//        List<Category> categories = this.categoryService.findAllCategory();
//        return toAllCategoryDto(categories);
//    }
//
//    @GetMapping("/{id}")
//    @Secured("ROLE_ADMIN")
//    @SecurityRequirement(name = "Authorization")
//    public ResponseEntity<Object> readById(@PathVariable long id) {
//        try {
//           return ResponseEntity.ok(toCategoryResp(this.categoryService.raedById(id)));
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(404).body(e.getMessage());
//        }
//    }
//
//    @Secured("ROLE_USER")
//    @GetMapping("/name/{name}")
//    @SecurityRequirement(name = "Authorization")
//    @ResponseStatus(HttpStatus.OK)
//    public CategoryRequest readByName(@PathVariable String name) {
//        Category category = this.categoryService.findCategoryByName(name);
//        if (category.getStatus().equals(EntityStatus.on)) {
//            return toCategory(category);
//        } else {
//            throw new EntityNotFoundException("Категория не существует");
//        }
//    }
//
//    @Secured("ROLE_ADMIN")
//    @PostMapping
//    @SecurityRequirement(name = "Authorization")
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) {
//        return ResponseEntity.ok(this.categoryService.createCategory(categoryRequest));
//    }
//
//    @Secured("ROLE_ADMIN")
//    @PutMapping
//    @SecurityRequirement(name = "Authorization")
//    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
//    public void updateCategory(@RequestBody Category category) {
//        this.categoryService.updateCategory(category);
//    }
//
//    @Secured("ROLE_ADMIN")
//    @DeleteMapping("/{id}")
//    @SecurityRequirement(name = "Authorization")
//    @ResponseStatus(HttpStatus.OK)
//    public void deleteCategory (@PathVariable long id) {
//        this.categoryService.deleteCategoryById(id);
//    }
//}

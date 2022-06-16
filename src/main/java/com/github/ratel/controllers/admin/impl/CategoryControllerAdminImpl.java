package com.github.ratel.controllers.admin.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.admin.interf.CategoryControllerAdmin;
import com.github.ratel.entity.Category;
import com.github.ratel.payload.request.CategoryRequest;
import com.github.ratel.payload.response.CategoryResponse;
import com.github.ratel.payload.response.MessageResponse;
import com.github.ratel.services.CategoryService;
import com.github.ratel.utils.transfer_object.CategoryTransferObj;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/app/shop/")
@RestController(value = "categoryControllerAdmin")
public class CategoryControllerAdminImpl implements CategoryControllerAdmin, ApiSecurityHeader {

    private final CategoryService categoryService;

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CategoryResponse> createCategory(CategoryRequest categoryRequest) {
        return ResponseEntity.ok(CategoryTransferObj.fromCategory(
                this.categoryService.createCategory(CategoryTransferObj.toCategory(categoryRequest)))
        );
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<CategoryResponse> updateCategory(CategoryRequest categoryRequest, long id) {
        Category category = this.categoryService.findById(id);
        category.setName(categoryRequest.getName());
        return ResponseEntity.ok(CategoryTransferObj.fromCategory(this.categoryService.updateCategory(category)));
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MessageResponse> deleteCategory (long id) {
        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.ok(new MessageResponse("Категория удалена"));
    }
}


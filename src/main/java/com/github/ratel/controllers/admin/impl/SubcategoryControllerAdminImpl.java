package com.github.ratel.controllers.admin.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.admin.SubcategoryControllerAdmin;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.payload.request.SubcategoryRequest;
import com.github.ratel.payload.response.MessageResponse;
import com.github.ratel.payload.response.SubcategoryResponse;
import com.github.ratel.services.CategoryService;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.utils.transfer_object.SubcategoryTransferObj;
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
@RestController(value = "subcategoryControllerAdmin")
public class SubcategoryControllerAdminImpl implements SubcategoryControllerAdmin, ApiSecurityHeader {

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<SubcategoryResponse> createCategory(SubcategoryRequest subcategoryRequest) {
        return ResponseEntity.ok(
                SubcategoryTransferObj.fromSubcategory(
                        this.subcategoryService.create(
                        SubcategoryTransferObj.toSubcategory(subcategoryRequest),
                        this.categoryService.findById(subcategoryRequest.getCategoryId())
                        )
                )
        );
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<SubcategoryResponse> updateCategory(SubcategoryRequest subcategoryRequest, long id) {
        Subcategory subcategory = this.subcategoryService.findById(id);
        subcategory.setName(subcategoryRequest.getName());
        return ResponseEntity.ok(SubcategoryTransferObj.fromSubcategory(this.subcategoryService.update(subcategory)));
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MessageResponse> deleteSubcategory(long id) {
        this.subcategoryService.delete(id);
        return ResponseEntity.ok(new MessageResponse("Сабкатегория удалена"));
    }
}

package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.app.SubcategoryControllerApp;
import com.github.ratel.entity.Category;
import com.github.ratel.entity.Subcategory;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/app/shop/")
@RestController(value = "subcategoryControllerApp")
public class SubcategoryControllerAppImpl implements SubcategoryControllerApp, ApiSecurityHeader {

    private final CategoryService categoryService;

    private final SubcategoryService subcategoryService;

    @Override
    @CrossOrigin("*")
    public ResponseEntity<List<SubcategoryResponse>> readAll(long categoryId) {
        Category category = this.categoryService.findById(categoryId);
        return ResponseEntity.ok(this.subcategoryService.findByAll(category).stream()
                .sorted(Comparator.comparing(Subcategory::getId))
                .map(SubcategoryTransferObj::fromSubcategory)
                .collect(Collectors.toList())
        );
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<SubcategoryResponse> getById(long id) {
        return ResponseEntity.ok(SubcategoryTransferObj.fromSubcategory(this.subcategoryService.findById(id)));
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<SubcategoryResponse> getByName(String name) {
        return ResponseEntity.ok(SubcategoryTransferObj.fromSubcategory(this.subcategoryService.findByName(name)));
    }
}

package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.app.CategoryControllerApp;
import com.github.ratel.entity.Category;
import com.github.ratel.payload.response.CategoryResponse;
import com.github.ratel.services.CategoryService;
import com.github.ratel.utils.transfer_object.CategoryTransferObj;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@RestController(value = "categoryControllerApp")
public class CategoryControllerAppImpl implements CategoryControllerApp, ApiSecurityHeader {

    private final CategoryService categoryService;

    @Override
    @CrossOrigin("*")
    public ResponseEntity<List<CategoryResponse>> readAllCategory() {
        return ResponseEntity.ok(this.categoryService.findAllCategory().stream()
                .sorted(Comparator.comparing(Category::getId))
                .map(CategoryTransferObj::fromCategory)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<CategoryResponse> getById(Long id) {
        return ResponseEntity.ok(CategoryTransferObj.fromCategory(this.categoryService.findById(id)));
    }

    @Override
    public ResponseEntity<CategoryResponse> getByName(String name) {
        return ResponseEntity.ok(CategoryTransferObj.fromCategory(this.categoryService.findCategoryByName(name)));
    }
}

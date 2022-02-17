package com.github.ratel.controllers.app.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.app.ProductControllerApp;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.payload.response.ProductResponse;
import com.github.ratel.services.ProductService;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.utils.transfer_object.ProductTransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/app/shop/")
@RestController(value = "productControllerApp")
public class ProductControllerAppImpl implements ProductControllerApp, ApiSecurityHeader {

    private final ProductService productService;

    private final SubcategoryService subcategoryService;

    @Override
    @CrossOrigin("*")
    public ResponseEntity<List<ProductResponse>> findAll(long subcategoryId) {
        Subcategory subcategory = this.subcategoryService.findById(subcategoryId);
        return ResponseEntity.ok(
                this.productService.findAll(subcategory).stream()
                .map(ProductTransferObj::fromProduct)
                .collect(Collectors.toList())
        );
    }

    @Override
    @CrossOrigin("*")
    public ResponseEntity<ProductResponse> findById(long id) {
        return ResponseEntity.ok(ProductTransferObj.fromProduct(this.productService.findById(id)));
    }
}

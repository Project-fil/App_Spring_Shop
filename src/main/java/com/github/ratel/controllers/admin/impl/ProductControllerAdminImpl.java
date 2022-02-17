package com.github.ratel.controllers.admin.impl;

import com.github.ratel.controllers.ApiSecurityHeader;
import com.github.ratel.controllers.admin.ProductControllerAdmin;
import com.github.ratel.entity.Brand;
import com.github.ratel.entity.FileEntity;
import com.github.ratel.entity.Product;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.handlers.FileHandler;
import com.github.ratel.payload.request.ProductRequest;
import com.github.ratel.payload.response.MessageResponse;
import com.github.ratel.payload.response.ProductResponse;
import com.github.ratel.services.BrandService;
import com.github.ratel.services.ProductService;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.utils.transfer_object.ProductTransferObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/app/shop/")
@RestController(value = "productControllerAdmin")
public class ProductControllerAdminImpl implements ProductControllerAdmin, ApiSecurityHeader {

    private final FileHandler fileHandler;

    private final BrandService brandService;

    private final ProductService productService;

    private final SubcategoryService subcategoryService;

    @Override
    @Transactional
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ProductResponse> create(ProductRequest productRequest, List<MultipartFile> files) {
        Product product = ProductTransferObj.toProduct(new Product(), productRequest);
        Set<FileEntity> newFiles = new HashSet<>();
        if (Objects.nonNull(files)) {

            newFiles = files.stream().map(this.fileHandler::writeFile).collect(Collectors.toSet());
        }
        Brand brand = this.brandService.checkBrandById(productRequest.getBrandId());
        Subcategory subcategory = this.subcategoryService.findById(productRequest.getSubcategoryId());
        product.setFiles(newFiles);
        product.addBrand(brand);
        return ResponseEntity.ok(ProductTransferObj.fromProduct(this.productService.create(product, subcategory)));
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ProductResponse> update(ProductRequest productRequest) {
        return null;
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ProductResponse> updateImage(long id, MultipartFile file) {
        return null;
    }

    @Override
    @CrossOrigin("*")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<MessageResponse> delete(long id) {
        return null;
    }
}

package com.github.ratel.controllers;

import com.github.ratel.dto.ProductDto;
import com.github.ratel.entity.Product;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.exceptions.ProductException;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.services.impl.ProductService;
import com.github.ratel.utils.TransferObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@ApiImplicitParams(
        @ApiImplicitParam(
                name = "Authorization",
                value = "Access Token",
                required = true,
                paramType = "header",
                example = "Bearer access_token"
        )
)
public class ProductController {

    private final ProductService productService;

    private final SubcategoryService subcategoryService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        List<Product> products = this.productService.findAllProducts();
        return TransferObj.toAllProductDto(products);
    }

    @GetMapping("/search")
    public List<Product> search(
            @RequestParam(required = false) EntityStatus status,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String vendorCode) {
        List<Product> searchResult = new ArrayList<>();
        if (status == null && name == null && vendorCode == null) {
            searchResult = productService.findAllProducts();
        } else if (vendorCode != null) {
            Optional<Product> product = productService.findProductByVendorCode(vendorCode);
            if (product.isPresent()) {
                searchResult.add(product.get());
            }
        } else if (name != null && status != null) {
            Optional<Product> product = productService.findProductByNameAndStatus(status, name);
            if (product.isPresent()) {
                searchResult.add(product.get());
            }
        } else if (name != null) {
            Optional<Product> product = productService.findByName(name);
            if (product.isPresent()) {
                searchResult.add(product.get());
            }
        } else if (status != null) {
            searchResult = productService.findAllProductsByStatus(status);
        }

        return searchResult;
    }

    @GetMapping("/status")
    public List<ProductDto> findAllProductsByStatus(@PathVariable EntityStatus status) {
        List<Product> products = productService.findAllProductsByStatus(status);
        return TransferObj.toAllProductDto(products);
    }

    @GetMapping("/{vendorCode}")
    public ProductDto findProductByVendorCode(@PathVariable String vendorCode) {
        Product product = productService.findProductByVendorCode(vendorCode)
                .orElseThrow(() -> new ProductException("Not found product with needed vendor code"));
        return TransferObj.toProduct(product);
    }

    @GetMapping("/{productId}")
    public ProductDto findProductByProductId(@PathVariable long productId) {
        Product product = productService.findProductByProductId(productId)
                .orElseThrow(() -> new ProductException("Not found product with needed id!"));
        return TransferObj.toProduct(product);
    }

    @PostMapping("/{subcategoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@PathVariable long subcategoryId, @RequestBody ProductDto payload) {
        Subcategory subcategory = this.subcategoryService.findById(subcategoryId);
        Product product = TransferObj.toProducts(payload);
        product.setSubcategory(subcategory);
        subcategory.addProduct(product);
        this.productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public void updateProduct(@PathVariable long productId, @RequestBody ProductDto payload) {
        Product product = TransferObj.toProducts(payload);
        this.productService.editProduct(productId, product);
    }

    @PutMapping("/{productId}/status")
    public void updateProductStatus(@PathVariable long productId, @RequestBody EntityStatus status) {
        this.productService.updateProductStatusOff(productId, status);
    }
}

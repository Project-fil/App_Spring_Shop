package com.github.ratel.controllers;

import com.github.ratel.dto.ProductDto;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.entity.Product;
import com.github.ratel.exceptions.ProductException;
import com.github.ratel.services.impl.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    //GET /product
    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
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

    //GET /product/status
    @GetMapping("/status")
    public List<Product> findAllProductsByStatus(@PathVariable EntityStatus status) {
        return productService.findAllProductsByStatus(status);
    }

    //GET /product/{vendorCode}
    @GetMapping("/{vendorCode}")
    public Product findProductByVendorCode(@PathVariable String vendorCode) {
        return productService.findProductByVendorCode(vendorCode)
                .orElseThrow(() -> new ProductException("Not found product with needed vendor code"));
    }

    //GET /product/{productId}
    @GetMapping("/{productId}")
    public Product findProductByProductId(@PathVariable long productId) {
        return productService.findProductByProductId(productId)
                .orElseThrow(() -> new ProductException("Not found product with needed id!"));
    }

    //POST /product
    @PreAuthorize(value = "hasRole('admin')")
    @PostMapping
    public long createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    //PUT /product/{productId}
    @PostMapping("/{productId}")
    public Product editProduct(@PathVariable long productId, @RequestBody ProductDto productDto) {
        return productService.editProduct(productId, productDto);
    }

    //DELETE /product/{productId}
    @DeleteMapping("/{productId}")
    public void deleteOrder(@PathVariable long productId) {
        productService.deleteProduct(productId);
    }
}

package com.github.ratel.controllers;

import com.github.ratel.dto.ProductDto;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.entity.Product;
import com.github.ratel.exceptions.ProductException;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.services.impl.ProductService;
import com.github.ratel.utils.TransferObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //GET /product
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

    //GET /product/status
    @GetMapping("/status")
    public List<ProductDto> findAllProductsByStatus(@PathVariable EntityStatus status) {
        List<Product> products = productService.findAllProductsByStatus(status);
        return TransferObj.toAllProductDto(products);
    }

    //GET /product/{vendorCode}
    @GetMapping("/code/{vendorCode}")
    public ProductDto findProductByVendorCode(@PathVariable String vendorCode) {
        Product product = productService.findProductByVendorCode(vendorCode)
                .orElseThrow(() -> new ProductException("Not found product with needed vendor code"));
        return TransferObj.toProduct(product);
    }

    //GET /product/{productId}
    @GetMapping("/{productId}")
    public ProductDto findProductByProductId(@PathVariable long productId) {
        Product product = productService.findProductByProductId(productId)
                .orElseThrow(() -> new ProductException("Not found product with needed id!"));
        return TransferObj.toProduct(product);
    }

    //POST /product
    @PostMapping("/{subcategoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@PathVariable Long subcategoryId, @RequestBody ProductDto payload) {
        Subcategory s = this.subcategoryService.findById(subcategoryId);
        Product product = TransferObj.toProductos(payload);
        product.setSubcategory(s);
        s.addProduct(product);
        this.productService.createProduct(product);
    }

    //PUT /product/{productId}
    @PutMapping("/{productId}")
    public void editProduct(@PathVariable long productId, @RequestBody ProductDto payload) {
        Product product = TransferObj.toProductos(payload);
        this.productService.editProduct(productId, product);
    }

    //DELETE /product/{productId}
    @DeleteMapping("/{productId}")
    public void deleteOrder(@PathVariable long productId) {
        this.productService.deleteProduct(productId);
    }
}

package com.github.ratel.services.impl;

import com.github.ratel.entity.Product;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl {

    // TODO: 06.02.2022 refactor all

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllProductsByStatus(EntityStatus status) {
        return productRepository.findAllByStatus(status);
    }

    public Product findProductByProductId(long productId) {
        return this.productRepository.findById(productId)
                .orElseThrow();
    }

    public Product findProductByVendorCode(String vendorCode) {
        return this.productRepository.findProductByVendorCode(vendorCode)
                .orElseThrow();
    }

    public Optional<Product> findProductByNameAndStatus(EntityStatus status, String name) {
        return this.productRepository.findProductByNameAndStatus(status, name);
    }

    public void createProduct(Product product) {
        this.productRepository.save(product);
    }

    public void editProduct(long productId, Product product) {
        Product newProduct = findProductByProductId(productId);
        newProduct.setVendorCode(product.getVendorCode());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        this.productRepository.save(newProduct);
    }

    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }
}

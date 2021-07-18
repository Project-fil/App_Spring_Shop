package com.github.ratel.services.impl;

import com.github.ratel.entity.Product;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findAllProductsByStatus(EntityStatus status) {
        return productRepository.findAllByStatus(status);
    }

    public Optional<Product> findProductByProductId(long productId) {
        Optional<Product> product = this.productRepository.findById(productId);
        if(product.get().getStatus().equals(EntityStatus.on)) {
            return product;
        } else {
            throw new EntityNotFound("Product not found");
        }
    }

    public Optional<Product> findProductByVendorCode(String vendorCode) {
        Optional<Product> product = this.productRepository.findProductByVendorCode(vendorCode);
        if(product.get().getStatus().equals(EntityStatus.on)) {
            return product;
        } else {
         throw new EntityNotFound("Product not found");
        }
    }

    public Optional<Product> findProductByNameAndStatus(EntityStatus status, String name) {
        return this.productRepository.findProductByNameAndStatus(status, name);
    }

    public void createProduct(Product product) {
        product.setStatus(EntityStatus.on);
        this.productRepository.save(product);
    }

    public void editProduct(long productId, Product product) {
        Product newProduct = findProductByProductId(productId).orElseThrow(() -> new RuntimeException("Not found product!"));
        newProduct.setVendorCode(product.getVendorCode());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setQuantity(product.getQuantity());
        this.productRepository.save(newProduct);
    }

    public void updateProductStatusOff(long productId, EntityStatus status) {
        Product product = this.productRepository.findById(productId).orElseThrow();
        status = product.getStatus();
        if (status.equals(EntityStatus.on)) {
            product.setStatus(EntityStatus.off);
            this.productRepository.save(product);
        } else {
            throw new EntityNotFound("Product not found");
        }
    }

    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }
}

package com.github.ratel.services.impl;

import com.github.ratel.dto.ProductDto;
import com.github.ratel.entity.EntityStatus;
import com.github.ratel.entity.Product;
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
        return productRepository.findById(productId);
    }

    public Optional<Product> findProductByVendorCode(String vendorCode) {
        return productRepository.findProductByVendorCode(vendorCode);
    }

    public Optional<Product> findProductByNameAndStatus(EntityStatus status, String name) {
        return productRepository.findProductByNameAndStatus(status, name);
    }

    public long createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setVendorCode(productDto.getVendorCode());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product).getId();
    }

    public Product editProduct(long productId, ProductDto productDto) {
        Product product = findProductByProductId(productId).orElseThrow(() -> new RuntimeException("Not found product!"));
        product.setVendorCode(productDto.getVendorCode());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return productRepository.save(product);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}

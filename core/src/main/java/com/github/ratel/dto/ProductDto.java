package com.github.ratel.dto;

import com.github.ratel.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String name;

    private BigDecimal price;

    private String brand;

    private long quantity;

    private String article;

    private String img;

    private String supplier;

    private String specification;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.brand = product.getBrand();
        this.quantity = product.getQuantity();
        this.article = product.getArticle();
        this.img = product.getImg();
        this.supplier = product.getSupplier();
        this.specification = product.getSpecification();
    }
}

package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long product_id;

    private String name;

    private BigDecimal price;

    private String brand;

    private long quantity;

    private String article;

    private String img;

    private String supplier;

    private String specification;

    private String comments;
}

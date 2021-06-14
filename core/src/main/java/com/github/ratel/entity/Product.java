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
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private Long id;

    @Column(name= "article", nullable = false)
    private String article;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name= "description", nullable = false)
    private String description;

    @Column(name= "price", nullable = false)
    private BigDecimal price;

    @Column(name= "brand", nullable = false)
    private String brand;

    @Column(name= "quantity", nullable = false)
    private long quantity;
}

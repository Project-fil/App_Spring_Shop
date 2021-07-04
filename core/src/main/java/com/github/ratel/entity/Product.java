package com.github.ratel.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long productId;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL")
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand")
    private Brand brand;

    @Column(name = "quantity", nullable = false, columnDefinition = "BIGINT")
    private long quantity;

    @Column(name = "article", nullable = false, columnDefinition = "TEXT")
    private String article;

    @Column(name = "img", nullable = false, columnDefinition = "TEXT")
    private String img;

    @Column(name = "supplier", nullable = false, columnDefinition = "TEXT")
    private String supplier;

    @Column(name = "specification", nullable = false, columnDefinition = "TEXT")
    private String specification;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    public Product(
            String name,
            BigDecimal price,
            Brand brand,
            long quantity,
            String article,
            String img,
            String supplier,
            String specification
    ) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.article = article;
        this.img = img;
        this.supplier = supplier;
        this.specification = specification;
    }

}

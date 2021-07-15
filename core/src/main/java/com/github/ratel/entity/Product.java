package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private Long id;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private Subcategory subcategory;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    public Product(String vendorCode, String name, BigDecimal price, long quantity) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

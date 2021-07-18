package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private Subcategory subcategory;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "product")
    private List<Specification> specification;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @OneToMany(mappedBy = "product")
    private Set<Comment> comments;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    public Product(String vendorCode, String name, BigDecimal price, long quantity) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String vendorCode, String name, BigDecimal price, long quantity, Category category, Subcategory subcategory) {
        this.vendorCode = vendorCode;
        this.category = category;
        this.subcategory = subcategory;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

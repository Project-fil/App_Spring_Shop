package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subcategories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BIGINT")
    private long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    public Subcategory(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subcategory(long id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Subcategory(long id, String name, Category category, List<Product> products) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}

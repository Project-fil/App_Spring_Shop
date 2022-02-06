package com.github.ratel.entity;

import com.github.ratel.entity.enums.EntityStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "subcategories")
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "subcategory",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Product> products = new ArrayList<>();

    @Column(name = "is_removed")
    private boolean removed;

    public Subcategory(String name) {
        this.name = name;
    }

    public Subcategory(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public Subcategory(String name, Category category, List<Product> products) {
        this.name = name;
        this.category = category;
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}

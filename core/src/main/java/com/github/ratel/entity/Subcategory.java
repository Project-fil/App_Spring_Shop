package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Product> products = new HashSet<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    public Subcategory(String name, Category category, Set<Product> products) {
        this.name = name;
        this.category = category;
        this.products = products;
    }
}

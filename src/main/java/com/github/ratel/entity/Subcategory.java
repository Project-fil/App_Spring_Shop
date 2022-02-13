package com.github.ratel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "subcategory",
            cascade = CascadeType.ALL
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
            )
    private List<Product> products = new ArrayList<>();

    @Column(name = "is_removed")
    private boolean removed;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date cratedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

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
        if (this.products.isEmpty())
            this.products = new ArrayList<>();
        this.products.add(product);
    }
}

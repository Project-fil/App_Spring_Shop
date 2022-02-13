package com.github.ratel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "discount")
    private int discount;

    @Column(name = "previewUrl")
    private String previewUrl;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "products_brand",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @Column(name = "removed")
    private boolean removed;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date cratedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Brand(String name) {
        this.name = name;
    }

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addProduct(Product product) {
        if (this.products.isEmpty())
            this.products = new ArrayList<>();
        this.products.add(product);
    }
}

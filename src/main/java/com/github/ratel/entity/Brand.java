package com.github.ratel.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE brands SET is_removed = true WHERE id=?")
@Where(clause = "is_removed=false")
public class Brand implements Serializable {

    @Transient
    private static final long serialVersionUID = 3022905791477242674L;

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
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "products_brand",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Brand that = (Brand) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}

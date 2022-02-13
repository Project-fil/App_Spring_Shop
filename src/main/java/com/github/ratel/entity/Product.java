package com.github.ratel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@ToString
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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "vendor_code", nullable = false)
    private String vendorCode;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ToString.Exclude
    @OneToMany()
    @JoinColumn(name = "file_id")
    private Set<FileEntity> files = new HashSet<>();

    @Column(name = "quantity", nullable = false)
    private int quantity;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", referencedColumnName = "id")
    private Subcategory subcategory;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "products_brand",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private Set<Brand> brands = new HashSet<>();

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "basket_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "basket_id")
    )
    private List<Basket> baskets = new ArrayList<>();

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orderList = new ArrayList<>();


    @OneToMany(mappedBy = "product")
    private Set<Comment> comments;

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

    public Product(String vendorCode, String name, BigDecimal price, int quantity) {
        this.vendorCode = vendorCode;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String vendorCode, String name, BigDecimal price, Subcategory subcategory) {
        this.vendorCode = vendorCode;
        this.subcategory = subcategory;
        this.name = name;
        this.price = price;
    }

    public void addBrand(Brand brand) {
        if (this.brands.isEmpty())
            this.brands = new HashSet<>();
        this.brands.add(brand);
    }

    public void addFile(FileEntity fileEntity) {
        if (this.files.isEmpty())
            this.files = new HashSet<>();
        this.files.add(fileEntity);
    }

    public void addComment(Comment comment) {
        if (this.comments.isEmpty())
            this.comments = new HashSet<>();
        this.comments.add(comment);
    }

}

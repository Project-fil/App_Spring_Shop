package com.github.ratel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    //    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Subcategory> subcategories = new HashSet<>();

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


    public Category(String name) {
        this.name = name;
    }


    public void addSubcategory(Subcategory subcategory) {
        if (subcategories.isEmpty())
            subcategories = new HashSet<>();
        this.subcategories.add(subcategory);
    }

}
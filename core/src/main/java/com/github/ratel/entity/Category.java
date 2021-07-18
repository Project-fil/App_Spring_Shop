package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "BIGINT")
    private long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(
            fetch = FetchType.LAZY, 
            mappedBy = "category", 
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private Set<Subcategory> subcategories = new HashSet<>();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status;


    public Category(String name) {
        this.name = name;
    }


    public void addSubcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
    }

}
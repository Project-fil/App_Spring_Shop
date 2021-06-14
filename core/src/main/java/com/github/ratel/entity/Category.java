package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false, unique = true, columnDefinition = "BIGINT")
    private long categoryId;

    @Column(name = "category_name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String categoryName;

    @Column(name = "subcategory_id", nullable = false, columnDefinition = "BIGINT")
    private String subcategoryId;

}

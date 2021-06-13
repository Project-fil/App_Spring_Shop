package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subcategory_id", nullable = false, unique = true, columnDefinition = "BIGINT")
    private long category_id;

    @Column(name = "subcategory_name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String category_name;

    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT")
    private String subcategory_id;
}

package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", nullable = false, unique = true, columnDefinition = "BIGINT")
    private long brandId;

    @Column(name = "brand_name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String brandName;

}

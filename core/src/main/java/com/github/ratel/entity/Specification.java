package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "specifications")
@NoArgsConstructor
@AllArgsConstructor
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "description")
    private String description;

    @Column(name = "measure")
    private String measure;

    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "name")
    private Brand brand;

    @Column(name = "picture")
    private String urlOfPicture;

}

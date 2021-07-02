package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "brand_name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String brandName;

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return id == brand.id && Objects.equals(brandName, brand.brandName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brandName);
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                '}' + "\n";
    }
}

package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
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
    @Column(name = "name", nullable = false, unique = true, columnDefinition = "TEXT")
    private String brandName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EntityStatus status;

    public Brand(String brandName) {
        this.brandName = brandName;
    }

    public Brand(String brandName, EntityStatus status) {
        this.brandName = brandName;
        this.status = status;
    }
}

package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Builder
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
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EntityStatus status;

    public Brand(String name) {
        this.name = name;
    }

    public Brand(String name, EntityStatus status) {
        this.name = name;
        this.status = status;
    }
}

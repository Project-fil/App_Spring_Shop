package com.github.ratel.payload.response;

import com.github.ratel.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;

    private String name;

    private String vendorCode;

    private String description;

    private BigDecimal price;

    private Set<FileEntityResponse> files = new HashSet<>();

    private int quantity;

    private String subcategoryName;

    private Set<BrandResponse> brands = new HashSet<>();

    private Set<CommentResponse> comments;

    private boolean removed;

    private Date cratedAt;

    private Date updatedAt;

    public ProductResponse(Long id,
                           String name,
                           String vendorCode,
                           String description,
                           BigDecimal price,
                           Set<FileEntityResponse> files,
                           int quantity,
                           String subcategoryName,
                           Set<CommentResponse> comments,
                           boolean removed,
                           Date cratedAt,
                           Date updatedAt) {
        this.id = id;
        this.name = name;
        this.vendorCode = vendorCode;
        this.description = description;
        this.price = price;
        this.files = files;
        this.quantity = quantity;
        this.subcategoryName = subcategoryName;
        this.comments = comments;
        this.removed = removed;
        this.cratedAt = cratedAt;
        this.updatedAt = updatedAt;
    }
}

package com.github.ratel.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {

    private Long id;

    private String name;

    private String country;

    private int discount;

    private String previewUrl;

    private List<ProductResponse> products = new ArrayList<>();

    private boolean removed;

    private Date cratedAt;

    private Date updatedAt;

    public BrandResponse(Long id,
                         String name,
                         String country,
                         int discount,
                         String previewUrl,
                         boolean removed,
                         Date cratedAt,
                         Date updatedAt) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.discount = discount;
        this.previewUrl = previewUrl;
        this.removed = removed;
        this.cratedAt = cratedAt;
        this.updatedAt = updatedAt;
    }
}

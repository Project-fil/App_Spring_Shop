package com.github.ratel.dto;

import com.github.ratel.entity.Category;
import com.github.ratel.entity.Subcategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class ProductDto {

    private String vendorCode;

    private String name;

    private BigDecimal price;

    private long quantity;

}

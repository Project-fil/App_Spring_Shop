package com.github.ratel.dto;

import com.github.ratel.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryDto {

    @NotBlank
    private String name;

    private Category category;

    private Set<ProductDto> products;

}

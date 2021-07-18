package com.github.ratel.dto;

import com.github.ratel.entity.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoryDto {

    private long id;

    @NotBlank
    private String name;

    private List<ProductDto> products = new ArrayList<>();

}

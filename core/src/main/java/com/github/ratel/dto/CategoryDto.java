package com.github.ratel.dto;

import com.github.ratel.payload.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @NotNull
    private String name;

    private Set<SubcategoryDto> subcategories = new HashSet<>();

    private EntityStatus status;

}

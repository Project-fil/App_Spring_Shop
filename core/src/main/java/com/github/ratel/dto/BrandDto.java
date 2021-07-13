package com.github.ratel.dto;

import com.github.ratel.payload.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String brandName;

    @NotNull
    private EntityStatus status;

}

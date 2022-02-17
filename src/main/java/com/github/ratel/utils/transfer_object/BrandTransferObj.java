package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Brand;
import com.github.ratel.payload.response.BrandResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class BrandTransferObj {

    public static BrandResponse fromBrand(Brand payload) {
        if (Objects.isNull(payload)){
         return null;
        }
        return new BrandResponse(

        );
    }
}

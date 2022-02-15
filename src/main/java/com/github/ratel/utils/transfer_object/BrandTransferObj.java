package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Brand;
import com.github.ratel.payload.response.BrandResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandTransferObj {

    public static BrandResponse fromBrand(Brand payload) {
        return new BrandResponse(

        );
    }
}

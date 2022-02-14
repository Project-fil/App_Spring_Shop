package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Product;
import com.github.ratel.payload.response.ProductResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransferObject {

    // TODO: 14.02.2022 write realisation

    public static ProductResponse fromProduct(Product payload) {
        return new ProductResponse(

        );
    }
}

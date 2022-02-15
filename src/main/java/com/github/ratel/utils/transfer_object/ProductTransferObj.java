package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Product;
import com.github.ratel.payload.response.ProductResponse;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class ProductTransferObj {

    public static ProductResponse fromProduct(Product payload) {
        return new ProductResponse(
                payload.getId(),
                payload.getName(),
                payload.getVendorCode(),
                payload.getDescription(),
                payload.getPrice(),
                payload.getFiles().stream().map(FileTransferObj::fromFile).collect(Collectors.toSet()),
                payload.getQuantity(),
                payload.getSubcategory().getName(),
                payload.getBrands().stream().map(BrandTransferObj::fromBrand).collect(Collectors.toSet()),
                payload.getComments().stream().map(CommentsTransferObj::fromComment).collect(Collectors.toSet()),
                payload.isRemoved(),
                payload.getCratedAt(),
                payload.getUpdatedAt()
        );
    }
}

package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Subcategory;
import com.github.ratel.payload.response.SubcategoryResponse;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class SubcategoryTransferObject {

    public static SubcategoryResponse fromSubcategory(Subcategory payload) {
       return new SubcategoryResponse(
                payload.getId(),
                payload.getName(),
                CategoryTransferObj.fromCategoryWithoutSubcategory(payload.getCategory()),
                payload.getProducts().stream().map(ProductTransferObject::fromProduct).collect(Collectors.toList()),
                payload.isRemoved(),
                payload.getCratedAt(),
                payload.getUpdatedAt()
        );
    }

    public static SubcategoryResponse fromSubcategoryWithoutCategory(Subcategory payload) {
        return new SubcategoryResponse(
                payload.getId(),
                payload.getName(),
                payload.getProducts().stream().map(ProductTransferObject::fromProduct).collect(Collectors.toList()),
                payload.isRemoved(),
                payload.getCratedAt(),
                payload.getUpdatedAt()
        );
    }

}

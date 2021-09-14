package com.github.ratel.utils.transfer_object;

import com.github.ratel.dto.SubcategoryDto;
import com.github.ratel.entity.Category;
import com.github.ratel.payload.request.CategoryRequest;
import com.github.ratel.payload.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.github.ratel.utils.TransferObj.subcategoryDtoSet;

public class CategoryTransferObj {

    public static List<CategoryRequest> toAllCategoryDto(List<Category> categories) {
        List<CategoryRequest> convertToDto = new ArrayList<>();
        for (Category category : categories) {
            convertToDto.add(toCategory(category));
        }
        return convertToDto;
    }

    public static Category fromCategory(CategoryRequest categoryRequest) {
        return new Category(
                categoryRequest.getName()
        );
    }

    public static CategoryRequest toCategory(Category category) {
        Set<SubcategoryDto> categoryDtoSet = subcategoryDtoSet(category.getSubcategories());
        return new CategoryRequest(
                category.getName()
//                categoryDtoSet,
        );
    }

    public static CategoryResponse toCategoryResp(Category category) {
        return new CategoryResponse(
          category.getName()
        );
    }

}

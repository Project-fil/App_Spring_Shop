package com.github.ratel.services;

import com.github.ratel.dto.CategoryDto;
import com.github.ratel.entity.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAllCategory();

    CategoryDto findCategoryById(long id);

    Category raedById(long id);

    CategoryDto findCategoryByName(String name);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategoryById(long id);

}

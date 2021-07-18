package com.github.ratel.services;

import com.github.ratel.dto.CategoryDto;
import com.github.ratel.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    Category raedById(long id);

    Category findCategoryByName(String name);

    void createCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(long id);

}

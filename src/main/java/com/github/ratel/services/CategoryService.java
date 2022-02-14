package com.github.ratel.services;

import com.github.ratel.entity.Category;
import com.github.ratel.payload.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    Category findById(Long id);

    Category getById(Long id);

    Category findCategoryByName(String name);

    Category getCategoryByName(String name);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategoryById(Long id);

}

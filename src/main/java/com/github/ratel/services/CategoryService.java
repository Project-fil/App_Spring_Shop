package com.github.ratel.services;

import com.github.ratel.entity.Category;
import com.github.ratel.payload.request.CategoryRequest;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    Category findById(long id);

    Category findCategoryByName(String name);

    Category createCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategoryById(long id);

}

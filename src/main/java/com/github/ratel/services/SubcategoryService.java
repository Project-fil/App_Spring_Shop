package com.github.ratel.services;

import com.github.ratel.entity.Category;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.entity.enums.EntityStatus;

import java.util.List;

public interface SubcategoryService {

    List<Subcategory> findByAll(Category category);

    Subcategory findById(long id);

    Subcategory getById(long id);

    Subcategory findByName(String name);

    Subcategory getByName(String name);

    Subcategory create(Subcategory subcategory, Category category);

    Subcategory update(Subcategory subcategory);

    void delete(long id);
}

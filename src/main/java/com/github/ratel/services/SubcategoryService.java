package com.github.ratel.services;

import com.github.ratel.entity.Subcategory;
import com.github.ratel.entity.enums.EntityStatus;

import java.util.List;

public interface SubcategoryService {

    List<Subcategory> findByAllSubcategory();

    List<Subcategory> findAllSubcategoryByStatus();

    Subcategory findById(long id);

    Subcategory findByName(String name);

    void create(Subcategory subcategory);

    void update(Subcategory subcategory);

//    void delete(long id);
}

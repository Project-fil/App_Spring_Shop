package com.github.ratel.services;

import com.github.ratel.dto.SubcategoryDto;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.payload.EntityStatus;

import java.util.List;

public interface SubcategoryService {

    List<Subcategory> findByAllSubcategory();

    List<Subcategory> findAllSubcategoryByStatus(EntityStatus status);

    Subcategory findById(long id);

    Subcategory findByName(String name);

    void create(Subcategory subcategory);

    void update(Subcategory subcategory);

    void delete(long id);
}

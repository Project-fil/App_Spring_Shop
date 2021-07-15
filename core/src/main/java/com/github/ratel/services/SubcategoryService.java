package com.github.ratel.services;

import com.github.ratel.dto.SubcategoryDto;
import com.github.ratel.entity.Subcategory;

import java.util.List;

public interface SubcategoryService {

    List<Subcategory> findByAllSubcategory();

    SubcategoryDto findById(long id);

    SubcategoryDto findByName(String name);

    Subcategory create(Subcategory subcategory);

    Subcategory update(Subcategory subcategory);

    void delete(long id);
}

package com.github.ratel.services.impl;

import com.github.ratel.entity.Subcategory;
import com.github.ratel.repositories.SubcategoryRepository;
import com.github.ratel.services.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {

    // TODO: 06.02.2022 refactor all

    private final SubcategoryRepository subcategoryRepository;


    @Override
    public List<Subcategory> findByAllSubcategory() {
        return null;
    }

    @Override
    public List<Subcategory> findAllSubcategoryByStatus() {
        return null;
    }

    @Override
    public Subcategory findById(long id) {
        return null;
    }

    @Override
    public Subcategory findByName(String name) {
        return null;
    }

    @Override
    public void create(Subcategory subcategory) {

    }

    @Override
    public void update(Subcategory subcategory) {

    }
}
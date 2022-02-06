package com.github.ratel.services.impl;

import com.github.ratel.entity.Subcategory;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.repositories.SubcategoryRepository;
import com.github.ratel.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {

    // TODO: 06.02.2022 refactor all

    private final SubcategoryRepository repository;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Subcategory> findByAllSubcategory() {
        return this.repository.findAll();
    }

    @Override
    public List<Subcategory> findAllSubcategoryByStatus() {
        return this.repository.findAllByRemovedFalse();
    }

    @Override
    public Subcategory findById(long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Subcategory findByName(String name) {
        return this.repository.findByName(name).orElseThrow();
    }

    @Override
    public void create(Subcategory subcategory) {
        this.repository.save(subcategory);
    }

    @Override
    public void update(Subcategory subcategory) {
        Subcategory updateSubcategory = this.repository.getById(subcategory.getId());
        updateSubcategory.setName(subcategory.getName());
        this.repository.save(updateSubcategory);
    }

//    @Override
//    public void delete(long id) {
//}

}
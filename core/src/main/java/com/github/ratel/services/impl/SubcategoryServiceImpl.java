package com.github.ratel.services.impl;

import com.github.ratel.entity.Subcategory;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.SubcategoryRepository;
import com.github.ratel.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubcategoryServiceImpl implements SubcategoryService {

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
    public List<Subcategory> findAllSubcategoryByStatus(EntityStatus status) {
        return this.repository.findAllByStatus(status);
    }

    @Override
    public Subcategory findById(long id) {
        Subcategory subcategory = this.repository.findById(id).orElseThrow();
        if (subcategory.getStatus().equals(EntityStatus.on)) {
            return subcategory;
        } else {
            throw new EntityNotFound("Subcategory does not exist");
        }
    }

    @Override
    public Subcategory findByName(String name) {
        Subcategory subcategory = this.repository.findByName(name).orElseThrow();
        if (subcategory.getStatus().equals(EntityStatus.on)) {
            return subcategory;
        }
        throw new EntityNotFound("Subcategory does not exist");
    }

    @Override
    public void create(Subcategory subcategory) {
        subcategory.setStatus(EntityStatus.on);
        this.repository.save(subcategory);
    }

    @Override
    public void update(Subcategory subcategory) {
        Subcategory updateSubcategory = this.repository.getById(subcategory.getId());
        updateSubcategory.setName(subcategory.getName());
        this.repository.save(updateSubcategory);
    }

    @Override
    public void delete(long id) {
        Subcategory subcategory = this.repository.findById(id).orElseThrow();
        if (subcategory.getStatus().equals(EntityStatus.on)) {
            subcategory.setStatus(EntityStatus.off);
            this.repository.save(subcategory);
        } else {
            throw new EntityNotFound("Subcategory not found");
        }
    }
}

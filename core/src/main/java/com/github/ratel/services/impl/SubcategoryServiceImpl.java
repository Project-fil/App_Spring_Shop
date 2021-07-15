package com.github.ratel.services.impl;

import com.github.ratel.dto.SubcategoryDto;
import com.github.ratel.entity.Subcategory;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.SubcategoryRepository;
import com.github.ratel.services.SubcategoryService;
import com.github.ratel.utils.TransferObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository repository;

    @Autowired
    public SubcategoryServiceImpl(SubcategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubcategoryDto> findByAllSubcategory() {
        List<Subcategory> subcategories = this.repository.findAll();
        return TransferObj.toAllSubcategoryDto(subcategories).stream()
                .filter(subcategory -> subcategory.equals(EntityStatus.on))
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoryDto findById(long id) {
        Subcategory subcategory = this.repository.findById(id).orElseThrow();
        if (subcategory.equals(EntityStatus.on)) {
            return TransferObj.toSubcategory(subcategory);
        }
        throw new EntityNotFound("Subcategory does not exist");
    }

    @Override
    public SubcategoryDto findByName(String name) {
        Subcategory subcategory = this.repository.findByName(name).orElseThrow();
        if (subcategory.equals(EntityStatus.on)) {
            return TransferObj.toSubcategory(subcategory);
        }
        throw new EntityNotFound("Subcategory does not exist");
    }

    @Override
    public Subcategory create(Subcategory subcategory) {
        subcategory.setStatus(EntityStatus.on);
        return this.repository.save(subcategory);
    }

    @Override
    public Subcategory update(Subcategory subcategory) {
        Subcategory updateSubcategory = this.repository.getById(subcategory.getId());
        updateSubcategory.setName(subcategory.getName());
        return this.repository.save(updateSubcategory);
    }

    @Override
    public void delete(long id) {
        Subcategory subcategory = this.repository.findById(id).orElseThrow();
        if (subcategory.getStatus().equals(EntityStatus.on)) {
            subcategory.setStatus(EntityStatus.off);
            this.repository.save(subcategory);
        }
        throw new EntityNotFound("Subcategory not found");
    }
}

package com.github.ratel.services.impl;

import com.github.ratel.dto.CategoryDto;
import com.github.ratel.entity.Category;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.CategoryRepository;
import com.github.ratel.services.CategoryService;
import com.github.ratel.utils.TransferObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategory() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category raedById(long id) {
        return this.categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public Category findCategoryByName(String name) {
        return this.categoryRepository.findByName(name).orElseThrow();
    }

    @Override
    public void createCategory(Category category) {
       category.setStatus(EntityStatus.on);
       this.categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        Category updateCategory = this.categoryRepository.findById(category.getId()).orElseThrow();
        updateCategory.setName(category.getName());
        this.categoryRepository.save(updateCategory);
    }

    @Override
    public void deleteCategoryById(long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow();
        if(category.getStatus().equals(EntityStatus.on)) {
            category.setStatus(EntityStatus.off);
            this.categoryRepository.save(category);
        }
        throw new EntityNotFound("Category not found");
    }
}

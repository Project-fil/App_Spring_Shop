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
    public List<CategoryDto> findAllCategory() {
         List<Category> categories = this.categoryRepository.findAll();
        return TransferObj.toAllCategoryDto(categories).stream()
                .filter(category -> category.getStatus().equals(EntityStatus.on))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findCategoryById(long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow();
        if(category.getStatus().equals(EntityStatus.on)) {
            return TransferObj.toCategory(category);
        }
        throw new EntityNotFound("Category does not exist");
    }

    @Override
    public CategoryDto findCategoryByName(String name) {
        Category category = this.categoryRepository.findByName(name).orElseThrow();
        if(category.getStatus().equals(EntityStatus.on)) {
            return TransferObj.toCategory(category);
        }
        throw new EntityNotFound("Category does not exist");
    }

    @Override
    public Category createCategory(Category category) {
       category.setStatus(EntityStatus.on);
       return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category updateCategory = this.categoryRepository.findById(category.getId()).orElseThrow();
        updateCategory.setName(category.getName());
        return this.categoryRepository.save(updateCategory);
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

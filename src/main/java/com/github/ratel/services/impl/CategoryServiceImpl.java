//package com.github.ratel.services.impl;
//
//import com.github.ratel.entity.Category;
//import com.github.ratel.entity.enums.EntityStatus;
//import com.github.ratel.payload.request.CategoryRequest;
//import com.github.ratel.repositories.CategoryRepository;
//import com.github.ratel.services.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Objects;
//
//@Service
//public class CategoryServiceImpl implements CategoryService {
//
//    private final CategoryRepository categoryRepository;
//
//    @Autowired
//    public CategoryServiceImpl(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @Override
//    public List<Category> findAllCategory() {
//        return this.categoryRepository.findAll();
//    }
//
//    @Override
//    public Category raedById(long id) {
//        Category category = this.categoryRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Категории не существует"));
//        if (Objects.nonNull(category) && category.getStatus().equals(EntityStatus.on)) {
//            return category;
//        } else {
//            throw new EntityNotFoundException("Категории не существует");
//        }
//    }
//
//    @Override
//    public Category findCategoryByName(String name) {
//        return this.categoryRepository.findByName(name)
//                .orElseThrow(() -> new EntityNotFoundException("Категории не существует"));
//    }
//
//    @Override
//    public Category createCategory(CategoryRequest categoryRequest) {
//        Category category = new Category();
//        category.setName(categoryRequest.getName());
//        category.setStatus(EntityStatus.on);
//        this.categoryRepository.save(category);
//        return category;
//    }
//
//    @Override
//    public void updateCategory(Category category) {
//        Category updateCategory = this.categoryRepository.findById(category.getId()).orElseThrow();
//        updateCategory.setName(category.getName());
//        this.categoryRepository.save(updateCategory);
//    }
//
//    @Override
//    public void deleteCategoryById(long id) {
//        Category category = this.categoryRepository.findById(id).orElseThrow();
//        if (category.getStatus().equals(EntityStatus.on)) {
//            category.setStatus(EntityStatus.off);
//            this.categoryRepository.save(category);
//        }
//        throw new EntityNotFoundException("Категории не существует");
//    }
//}

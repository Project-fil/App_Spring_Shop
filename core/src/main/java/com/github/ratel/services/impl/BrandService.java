package com.github.ratel.services.impl;

import com.github.ratel.entity.Brand;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAllBrand() {
        return this.brandRepository.findAll();
    }

    public Brand findBrandById(long id) {
        return this.brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Id missing"));
    }

    public Brand findBrandByName(String name) {
        return this.brandRepository.findByName(name).orElseThrow(() -> new RuntimeException("Name missing"));
    }

    public void saveBrand(Brand brand) {
        brand.setStatus(EntityStatus.on);
        this.brandRepository.save(brand);
    }

    public void updateBrandById(Brand brand) {
        Brand updateBrand = findBrandById(brand.getId());
        updateBrand.setName(brand.getName());
        this.brandRepository.save(updateBrand);
    }

    public void deleteBrandById(long id) {
        Brand brand = this.brandRepository.findById(id).orElseThrow(() -> new EntityNotFound("Entity not found in db"));
        if (brand.getStatus().equals(EntityStatus.on)) {
            brand.setStatus(EntityStatus.off);
            this.brandRepository.save(brand);
        }
    }
}

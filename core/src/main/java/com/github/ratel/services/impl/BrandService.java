package com.github.ratel.services.impl;

import com.github.ratel.dto.BrandDto;
import com.github.ratel.entity.Brand;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAllBrand() {
        return brandRepository.findAll();
    }

    public Optional<Brand> findBrandById(long id) {
        return brandRepository.findById(id);
    }

    public Optional<Brand> findBrandByName(String name) {
        return brandRepository.findByBrandName(name);
    }

    public Brand saveBrand(Brand brand) {
        Brand newBrand = new Brand();
        newBrand.setBrandName(brand.getBrandName());
        return brandRepository.save(newBrand);
    }

    public Brand updateBrandById(long id, Brand brand) {
        Brand brandUpdate = findBrandById(id).orElseThrow( () -> new EntityNotFound("Entity not found in db"));
        brandUpdate.setBrandName(brand.getBrandName());
        return brandRepository.save(brandUpdate);
    }

    public void deleteBrandById(long id) {
        brandRepository.deleteById(id);
    }
}

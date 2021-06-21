package com.github.ratel.services.impl;

import com.github.ratel.dto.BrandDto;
import com.github.ratel.entity.Brand;
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

    public Optional<Brand> findBrandById(long brandId) {
        return brandRepository.findById(brandId);
    }

    public Brand saveBrand(BrandDto brandDto) {
        Brand brand = new Brand();
        brand.setBrandName(brandDto.getBrandName());
        return brandRepository.save(brand);
    }

    public Brand updateBrandById(long brandId, BrandDto brandDto) {
        Brand brand = findBrandById(brandId).orElseThrow(() -> new RuntimeException("Not found brand"));
        brand.setBrandName(brandDto.getBrandName());
        return brandRepository.save(brand);
    }

    public void deleteBrandById(long brandId) {
        brandRepository.deleteById(brandId);
    }
}

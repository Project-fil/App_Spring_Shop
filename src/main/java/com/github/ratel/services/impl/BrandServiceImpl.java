package com.github.ratel.services.impl;

import com.github.ratel.entity.Brand;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.repositories.BrandRepository;
import com.github.ratel.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<Brand> findAllBrand() {
        return this.brandRepository.findAll();
    }

    public Brand findBrandById(long id) {
        return this.brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Нет такого бренда"));
    }

    public Brand findBrandByName(String name) {
        return this.brandRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Нет такого бренда"));
    }

    public void saveBrand(Brand brand) {
        this.brandRepository.save(brand);
    }

    public void updateBrandById(Brand brand) {
        Brand updateBrand = findBrandById(brand.getId());
        updateBrand.setName(brand.getName());
        this.brandRepository.save(updateBrand);
    }

//    public void deleteBrandById(long id) {
//        Brand brand = this.brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Нет такого бренда"));
//        if (brand.getStatus().equals(EntityStatus.on)) {
//            brand.setStatus(EntityStatus.off);
//            this.brandRepository.save(brand);
//        }
//    }
}

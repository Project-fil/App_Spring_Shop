package com.github.ratel.services;

import com.github.ratel.entity.Brand;

import java.util.List;

public interface BrandService {

    public List<Brand> findAllBrand();

    public Brand findBrandById(long id);

    public Brand findBrandByName(String name);

    public void saveBrand(Brand brand);

    public void updateBrandById(Brand brand);

//    public void deleteBrandById(long id);

}

package com.github.ratel.controllers;

import com.github.ratel.dto.BrandDto;
import com.github.ratel.entity.Brand;
import com.github.ratel.services.impl.BrandService;
import com.github.ratel.utils.TransferObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> findAllBrand() { return brandService.findAllBrand(); }

    @GetMapping("/{brandId}")
    public Brand findByIdBrand(@PathVariable long brandId) {
        return brandService.findBrandById(brandId).orElseThrow(()-> new RuntimeException("Id missing"));
    }

    @GetMapping("/{brandName}")
    public Brand findByName(@PathVariable String brandName) {
        return brandService.findBrandByName(brandName).orElseThrow(()-> new RuntimeException("Name missing"));
    }

    @PostMapping
    public Brand createBrand(@RequestBody BrandDto brandDto) {
        Brand brand = TransferObj.toBrand(brandDto);
        return brandService.saveBrand(brand);
    }

    @PutMapping("/{brandId}")
    public Brand updateBrand(@PathVariable long brandId, @RequestBody BrandDto brandDto) {
        Brand brand = TransferObj.toBrand(brandDto);
        return brandService.updateBrandById(brandId, brand);
    }

    @DeleteMapping("/{brandId}")
    public void deleteBrand(@PathVariable long brandId) {
        brandService.deleteBrandById(brandId);
    }

}

package com.github.ratel.controllers;

import com.github.ratel.dto.BrandDto;
import com.github.ratel.entity.Brand;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.services.impl.BrandService;
import com.github.ratel.utils.TransferObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<Brand> findAllBrand() {
       return this.brandService.findAllBrand();
    }

    @GetMapping("/{brandId}")
    public BrandDto findByIdBrand(@PathVariable long brandId) {
        Brand brand = this.brandService.findBrandById(brandId);
        return TransferObj.toBrand(brand);
    }

    @GetMapping("/name-{brandName}")
    public BrandDto findByName(@PathVariable("brandName") String brandName) {
        Brand brand = this.brandService.findBrandByName(brandName);
        return TransferObj.toBrand(brand);
    }

    @PostMapping
    @ApiImplicitParams(
            @ApiImplicitParam(
                    name = "Authorization",
                    value = "Access Token",
                    required = true,
                    paramType = "header",
                    example = "Bearer access_token"
            )
    )
    public Brand createBrand(@RequestBody BrandDto brandDto) {
        Brand brand = TransferObj.toBrand(brandDto);
        return brandService.saveBrand(brand);
    }

    @PutMapping
    public Brand updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrandById(brand);
    }

    @DeleteMapping("/{brandId}")
    public void deleteBrand(@PathVariable long brandId) {
        brandService.deleteBrandById(brandId);
    }

}

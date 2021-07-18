package com.github.ratel.controllers;

import com.github.ratel.dto.BrandDto;
import com.github.ratel.entity.Brand;
import com.github.ratel.exceptions.EntityNotFound;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.services.impl.BrandService;
import com.github.ratel.utils.TransferObj;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/brand")
@ApiImplicitParams(
        @ApiImplicitParam(
                name = "Authorization",
                value = "Access Token",
                required = true,
                paramType = "header",
                example = "Bearer access_token"
        )
)
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public List<Brand> findAllBrand() {
        return this.brandService.findAllBrand();
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    public List<BrandDto> findAllBrandFromUser() {
        List<Brand> brands = this.brandService.findAllBrand().stream()
                .filter(brand -> brand.getStatus().equals(EntityStatus.on))
                .collect(Collectors.toList());
        return TransferObj.toAllBrandDto(brands);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{brandId}")
    public Brand findByIdBrand(@PathVariable long brandId) {
        Brand brand = this.brandService.findBrandById(brandId);
        if (brand.getStatus().equals(EntityStatus.on)) {
            return brand;
        } else {
            throw new EntityNotFound("Brand not found");
        }
    }

    @Secured("ROLE_USER")
    @GetMapping("/name/{brandName}")
    public BrandDto findByName(@PathVariable String brandName) {
        Brand brand = this.brandService.findBrandByName(brandName);
        if (brand.getStatus().equals(EntityStatus.on)) {
            return TransferObj.toBrand(brand);
        } else {
            throw new EntityNotFound("Brand not found");
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public void createBrand(@RequestBody BrandDto brandDto) {
        Brand brand = TransferObj.toBrand(brandDto);
        brandService.saveBrand(brand);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public void updateBrand(@RequestBody Brand brand) {
        brandService.updateBrandById(brand);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{brandId}")
    public void deleteBrand(@PathVariable long brandId) {
        brandService.deleteBrandById(brandId);
    }

}

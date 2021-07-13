package com.github.ratel.repositories;


import com.github.ratel.entity.Brand;
import com.github.ratel.payload.EntityStatus;

import java.util.List;

public class BrandRepositoryMock {

    public static List<Brand> prepareBrandList() {
        return List.of(
                new Brand("Ceresit", EntityStatus.on),
                new Brand("Bubmaster", EntityStatus.on),
                new Brand("Feber", EntityStatus.on)
        );
    }

    public static List<Brand> expBrandList() {

        return List.of(brand_1(), brand_2(), brand_3());
    }

    public static Brand brand_1() {
        return new Brand(1L,
                "Ceresit", EntityStatus.on);
    }

    public static Brand brand_2() {
        return new Brand(2L,
                "Bubmaster", EntityStatus.on);
    }

    public static Brand brand_3() {
        return new Brand(3L,
                "Feber", EntityStatus.on);
    }


}

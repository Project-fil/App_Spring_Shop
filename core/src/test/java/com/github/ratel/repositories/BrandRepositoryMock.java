package com.github.ratel.repositories;


import com.github.ratel.entity.Brand;

import java.util.List;

public class BrandRepositoryMock {

    public static Brand brand_1() {
        return new Brand(1L,
                "Ceresit");
    }

    public static Brand brand_2() {
        return new Brand(2L,
                "Bubmaster");
    }

    public static Brand brand_3() {
        return new Brand(3L,
                "Feber");
    }

    public static List<Brand> brandList() {
        return List.of(brand_1(), brand_2(), brand_3());
    }
}

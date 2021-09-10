package com.github.ratel.utils;

import com.github.ratel.dto.*;
import com.github.ratel.entity.*;
import com.github.ratel.payload.request.CategoryRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransferObj {

    public static Order toOrder(OrderDto data) {
        return new Order(
                data.getOrder_item_id(),
                data.getPrice(),
                data.getCreatedAt(),
                data.getEmail(),
                data.getAddress()
        );
    }

    public static OrderDto fromOrder(Order data) {
        return new OrderDto(
                data.getOrderItemId(),
                data.getPrice(),
                data.getCreatedAt(),
                data.getEmail(),
                data.getAddress()
        );
    }

    public static List<BrandDto> toAllBrandDto (List<Brand> brands) {
        List<BrandDto> convertBrand = new ArrayList<>();
        for (Brand brand : brands) {
            convertBrand.add(toBrand(brand));
        }
        return convertBrand;
    }

    public static Brand toBrand(BrandDto data) {
        return new Brand(
                data.getName()
        );
    }

    public static BrandDto fromBrand(Brand data) {
        return new BrandDto(
                data.getName()
        );
    }

    public static BrandDto toBrand(Brand data) {
        return new BrandDto(
                data.getName()
        );
    }

    public static List<SubcategoryDto> toAllSubcategoryDto(List<Subcategory> subcategories) {
        List<SubcategoryDto> convertToDto = new ArrayList<>();
        for (Subcategory subcategory : subcategories) {
            convertToDto.add(toSubcategory(subcategory));
        }
        return convertToDto;
    }

    public static Set<SubcategoryDto> subcategoryDtoSet(Set<Subcategory> subcategories) {
        return subcategories.stream()
                .map(subcategory -> toSubcategory(subcategory))
                .collect(Collectors.toSet());
    }

    public static SubcategoryDto toSubcategory(Subcategory subcategory) {
        List<ProductDto> productDtoList = toProductDtos(subcategory.getProducts());
        return new SubcategoryDto(
                subcategory.getId(),
                subcategory.getName()
//                productDtoList
        );
    }

    public static Subcategory toSubcategoryFromUser(SubcategoryDto subcategoryDto) {
        return new Subcategory(
                subcategoryDto.getId(),
                subcategoryDto.getName()
        );
    }

    public static List<Product> toProductDt(List<ProductDto> products) {
        return products.stream()
                .map(product -> toProducts(product))
                .collect(Collectors.toList());
    }

    public static List<ProductDto> toProductDtos(List<Product> products) {
        return products.stream()
                .map(product -> toProduct(product))
                .collect(Collectors.toList());
    }


    public static ProductDto toProduct(Product product) {
        return new ProductDto(
                product.getVendorCode(),
                product.getCategory().getId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
    }

    public static Product toProducts(ProductDto productDto) {
        return new Product(
                productDto.getVendorCode(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getQuantity()
        );
    }

        public static List<ProductDto> toAllProductDto(List<Product> productList) {
        List<ProductDto> productDtoList = new ArrayList<>();
            for (Product product:productList) {
                productDtoList.add(toProduct(product));
            }
            return productDtoList;
        }

    }

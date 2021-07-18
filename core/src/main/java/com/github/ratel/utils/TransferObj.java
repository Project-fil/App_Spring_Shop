package com.github.ratel.utils;

import com.github.ratel.dto.*;
import com.github.ratel.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TransferObj {

    public static User toUser(UserAuthDto data) {
        return new User(
                data.getLogin(),
                data.getPassword()
        );
    }

    public static UserAuthDto fromUserAuth(User data) {
        return new UserAuthDto(
                data.getLogin(),
                data.getPassword()
        );
    }

    public static List<UserDto> toAllDto(List<User> users) {
        List<UserDto> convertToDto = new ArrayList<>();
        for (User user : users) {
            convertToDto.add(toDto(user));
        }
        return convertToDto;
    }

    public static UserDto toDto(User user) {
        Set<RoleDto> roleDTO = toDTO(user.getRoles());
        return new UserDto(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getLogin(),
                user.getPassword(),
                user.getPhone(),
                user.getAddress(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                roleDTO,
                user.getVerification(),
                user.getStatus()
        );
    }

    private static Set<RoleDto> toDTO(Set<Roles> role) {
        return role.stream()
                .map(role1 -> toDto(role1))
                .collect(Collectors.toSet());
    }

    private static RoleDto toDto(Roles roles) {
        return new RoleDto(
                roles.getId(),
                roles.getRole()
        );
    }

    public static User toUser(UserRegDto data) {
        return new User(
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getLogin(),
                data.getPassword(),
                data.getPhone(),
                data.getAddress(),
                data.getCreatedAt(),
                data.getVerification(),
                data.getStatus()
        );
    }

    public static UserRegDto fromUserReg(User data) {
        return new UserRegDto(
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getLogin(),
                data.getPassword(),
                data.getPhone(),
                data.getAddress(),
                data.getCreatedAt(),
                data.getRoles(),
                data.getVerification(),
                data.getStatus()
        );
    }

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

    public static List<CategoryDto> toAllCategoryDto(List<Category> categories) {
        List<CategoryDto> convertToDto = new ArrayList<>();
        for (Category category : categories) {
            convertToDto.add(toCategory(category));
        }
        return convertToDto;
    }

    public static Category toCategoryFromUser(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getName()
        );
    }

    public static CategoryDto toCategory(Category category) {
        Set<SubcategoryDto> categoryDtoSet = subcategoryDtoSet(category.getSubcategories());
        return new CategoryDto(
                category.getName(),
                categoryDtoSet,
                category.getStatus()
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
                subcategory.getName(),
                productDtoList
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

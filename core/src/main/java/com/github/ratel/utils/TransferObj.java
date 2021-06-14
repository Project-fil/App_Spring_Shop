package com.github.ratel.utils;

import com.github.ratel.dto.OrderDto;
import com.github.ratel.dto.ProductDto;
import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.Order;
import com.github.ratel.entity.Product;
import com.github.ratel.entity.User;

public class TransferObj {

    public static Product toProduct(ProductDto data) {
        return new Product(
                data.getName(),
                data.getPrice(),
                data.getBrand(),
                data.getQuantity(),
                data.getArticle(),
                data.getImg(),
                data.getSupplier(),
                data.getSpecification()
        );
    }

    public static ProductDto fromProduct(Product data) {
        return new ProductDto(
                data.getName(),
                data.getPrice(),
                data.getBrand(),
                data.getQuantity(),
                data.getArticle(),
                data.getImg(),
                data.getSupplier(),
                data.getSpecification()
        );
    }

    public static User toUser(UserAuthDto data) {
        return new User(
                data.getLogin(),
                data.getPassword()
        );
    }

    public static UserAuthDto fromUserAuth(User data) {
        return new UserAuthDto(
                data.getLogin(),
                data.getHashPassword()
        );
    }

    public static User toUser(UserRegDto data) {
        return new User(
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getLogin(),
                data.getHashPassword(),
                data.getPhone(),
                data.getAddress(),
                data.getCreatedAt(),
                data.getSalt()
        );
    }

    public static UserRegDto fromUserReg(User data) {
        return new UserRegDto(
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getLogin(),
                data.getHashPassword(),
                data.getPhone(),
                data.getAddress(),
                data.getCreatedAt(),
                data.getSalt()
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
}

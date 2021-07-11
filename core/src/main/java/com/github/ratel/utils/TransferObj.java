package com.github.ratel.utils;

import com.github.ratel.dto.*;
import com.github.ratel.entity.*;

import java.util.Optional;
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

    public static UserDto toDto(Optional<User> user) {
        Set<RoleDto> roleDTO = toDTO(user.get().getRoles());
        return new UserDto(
                user.get().getFirstname(),
                user.get().getLastname(),
                user.get().getEmail(),
                user.get().getLogin(),
                user.get().getPassword(),
                user.get().getPhone(),
                user.get().getAddress(),
                user.get().getCreatedAt(),
                user.get().getUpdatedAt(),
                roleDTO,
                user.get().getVerification(),
                user.get().getStatus()
                );
    }

    private static Set<RoleDto> toDTO(Set<Roles> role) {
        return role.stream().map(role1 -> toDto(role1)).collect(Collectors.toSet());
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

    public static Brand toBrand(BrandDto data) {
        return new Brand(data.getBrandName());
    }

    public static BrandDto fromBrand(Brand data) {
        return new BrandDto(data.getBrandName());
    }

    public static Comment toComment(CommentDto data) {
        return new Comment(
                data.getUserId(),
                data.getProductId(),
                data.getCommentText(),
                data.getCreatedAt()
        );
    }

    public static CommentDto fromComment(Comment data) {
        return new CommentDto(
                data.getProductId(),
                data.getUserId(),
                data.getCommentText(),
                data.getCreatedAt()
        );
    }
}

package com.github.ratel.utils;

import com.github.ratel.dto.*;
import com.github.ratel.entity.*;

import java.util.ArrayList;
import java.util.List;
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
        return new Brand(
                data.getBrandName(),
                data.getStatus()
        );
    }

    public static BrandDto fromBrand(Brand data) {
        return new BrandDto(
                data.getBrandName(),
                data.getStatus()
        );
    }

    public static BrandDto toBrand(Brand data) {
        return new BrandDto(
                data.getBrandName(),
                data.getStatus()
        );
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

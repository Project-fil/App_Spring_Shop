package com.github.ratel.utils;

import com.github.ratel.dto.RoleDto;
import com.github.ratel.dto.UserAuthDto;
import com.github.ratel.dto.UserDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.payload.request.CreateAdminRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserTransferObject {

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

//    public static User toUser(CreateAdminRequest data) {
//        return new User(
//                data.getFirstname(),
//                data.getLastname(),
//                data.getLogin(),
//                data.getEmail(),
//                data.getPassword(),
//                data.getConfirmPassword()
//        );
//    }

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
                data.getVerification(),
                data.getStatus()
        );
    }

}

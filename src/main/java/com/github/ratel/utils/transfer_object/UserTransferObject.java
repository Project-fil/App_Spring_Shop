package com.github.ratel.utils.transfer_object;

import com.github.ratel.dto.RoleDto;
import com.github.ratel.dto.UserDto;
import com.github.ratel.dto.UserRegDto;
import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.payload.request.UserAuthRequest;
import com.github.ratel.payload.response.UserResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserTransferObject {

    public static User toUser(UserAuthRequest data) {
        return new User(
                data.getEmail(),
                data.getPassword()
        );
    }

    public static UserAuthRequest fromUserAuth(User data) {
        return new UserAuthRequest(
                data.getEmail(),
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
//        Set<RoleDto> roleDTO = toDTO(user.getRoles());
        return new UserDto(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone()
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

    public static UserResponse fromUserReg(User data) {
        Set<RoleDto> roleDTO = toDTO(data.getRoles());
        return new UserResponse(
                data.getId(),
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getPhone(),
                data.getAddress(),
                data.getCreatedAt(),
                data.getUpdatedAt(),
                roleDTO,
                data.getVerification(),
                data.getStatus()
        );
    }

}

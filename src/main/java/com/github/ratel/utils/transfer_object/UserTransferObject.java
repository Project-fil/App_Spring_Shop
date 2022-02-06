package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.User;
import com.github.ratel.payload.response.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransferObject {

    public static UserResponse fromUser(User data) {
        return new UserResponse(
                data.getId(),
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                AddressTransferObject.fromAddress(data.getAddress()),
                data.getRoles(),
                data.getVerification(),
                data.isRemoved()
                );
    }

//    public static List<UserDto> toAllDto(List<User> users) {
//        List<UserDto> convertToDto = new ArrayList<>();
//        for (User user : users) {
//            convertToDto.add(toDto(user));
//        }
//        return convertToDto;
//    }

//    public static UserDto toDto(User user) {
////        Set<RoleDto> roleDTO = toDTO(user.getRoles());
//        return new UserDto(
//                user.getFirstname(),
//                user.getLastname(),
//                user.getEmail(),
//                user.getPassword()
//        );
//    }

}

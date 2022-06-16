package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.User;
import com.github.ratel.payload.request.UserUpdateRequest;
import com.github.ratel.payload.response.UserResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserTransferObj {

    public static UserResponse fromUser(User data) {
        return new UserResponse(
                data.getId(),
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                AddressTransferObj.fromAddressWithoutUser(data.getAddress()),
                data.getRoles(),
                data.getVerification(),
                data.isRemoved()
                );
    }

    public static UserResponse fromUserWithoutAddress(User data) {
        return new UserResponse(
                data.getId(),
                data.getFirstname(),
                data.getLastname(),
                data.getEmail(),
                data.getRoles(),
                data.getVerification(),
                data.isRemoved()
        );
    }

    public static User updateUser(User user, UserUpdateRequest payload) {
        user.setFirstname(payload.getFirstname());
        user.setLastname(payload.getLastname());
        user.getAddress().setPhone(payload.getPhone());
        user.getAddress().setCountry(payload.getCountry());
        user.getAddress().setCity(payload.getCity());
        user.getAddress().setStreet(payload.getStreet());
        user.getAddress().setHouseNumber(payload.getHouseNumber());
        user.getAddress().setApartmentNumber(payload.getApartmentNumber());
        return user;
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

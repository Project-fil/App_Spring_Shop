package com.github.ratel.dto;

import com.github.ratel.payload.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String phone;
    private String address;
    private Role role;
}

package com.github.ratel.dto;

import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.UserVerificationStatus;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String phone;

    private String address;

    private Date createdAt;

    private Date updatedAt;

    private Set<RoleDto> roles = new HashSet<>();

    private UserVerificationStatus verification;

    private EntityStatus status;

}

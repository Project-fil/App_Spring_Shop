package com.github.ratel.payload.response;

import com.github.ratel.dto.RoleDto;
import com.github.ratel.entity.Address;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.Roles;
import com.github.ratel.entity.enums.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private long id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private Address address;

    private Roles role;

    private UserVerificationStatus verification;

    private EntityStatus status;

}

package com.github.ratel.payload.response;

import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.Roles;
import com.github.ratel.entity.enums.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;

    private String firstname;

    private String lastname;

    private String email;

    private String phone;

    private AddressResponse address;

    private Roles role;

    private UserVerificationStatus verification;

    private EntityStatus status;

}

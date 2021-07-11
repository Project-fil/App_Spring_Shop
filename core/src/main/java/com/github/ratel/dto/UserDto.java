package com.github.ratel.dto;

import com.github.ratel.entity.Roles;
import com.github.ratel.entity.User;
import com.github.ratel.payload.EntityStatus;
import com.github.ratel.payload.UserVerificationStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstname;

    private String lastname;

    private String email;

    private String login;

    private String password;

    private String phone;

    private String address;

    private Date createdAt;

    private Date updatedAt;

    private Set<RoleDto> roles = new HashSet<>();

    private UserVerificationStatus verification;

    private EntityStatus status;

}

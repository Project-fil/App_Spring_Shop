package com.github.ratel.dto;

import com.github.ratel.payload.EntityStatus;
import com.github.ratel.payload.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstname;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastname;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 2, max = 20)
    private String login;

    @NotBlank
    @Size(min = 2, max = 70)
    private String password;

    @NotNull
    @Min(value = 0)
    private String phone;

    @NotNull
    private String address;

    @NotNull
    @PastOrPresent
    private Date createdAt = new Date();

    private Set<RoleDto> roles;

    @NotNull
    private UserVerificationStatus verification = UserVerificationStatus.UNVERIFIED;

    @NotNull
    private EntityStatus status = EntityStatus.on;

    public UserRegDto(String firstname, String lastname, String email, String login, String password, String phone, String address, Date createdAt,
                      UserVerificationStatus verification, EntityStatus status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.verification = verification;
        this.status = status;
    }
}

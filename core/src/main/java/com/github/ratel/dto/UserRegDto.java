package com.github.ratel.dto;

import com.github.ratel.payload.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstname;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastname;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String login;

    @NotEmpty
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

    @NotNull
    private UserVerificationStatus verification = UserVerificationStatus.UNVERIFIED;

}

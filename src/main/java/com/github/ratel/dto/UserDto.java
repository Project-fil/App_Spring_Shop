package com.github.ratel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, max = 40)
    private String password;

    @NotEmpty
    @Size(min = 8, max = 40)
    private String confirmPassword;

    @NotEmpty
    @Size(min = 9, max = 20)
    private String phone;

    public UserDto(String firstname, String lastname, String email, String password, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }
}

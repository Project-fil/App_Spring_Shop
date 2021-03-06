package com.github.ratel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPassDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 2, max = 70)
    private String newPassword;

    @NotBlank
    @Size(min = 2, max = 70)
    private String confirmPassword;

}

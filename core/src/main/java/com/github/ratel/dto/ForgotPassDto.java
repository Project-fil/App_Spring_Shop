package com.github.ratel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPassDto {

    @NotEmpty
    @Size(min = 2, max = 50)
    String login;

    @NotEmpty
    @Size(min = 2, max = 70)
    String newPassword;

    @NotEmpty
    @Size(min = 2, max = 70)
    String confirmPassword;
}

package com.github.ratel.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminRequest {

    private String firstname;

    private String lastname;

    private String login;

    private String email;

    private String password;

    private String confirmPassword;

}

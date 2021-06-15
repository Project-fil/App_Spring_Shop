package com.github.ratel.dto;

import com.github.ratel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegDto {


    private String firstname;

    private String lastname;

    private String email;

    private String login;

    private String hashPassword;

    private String phone;

    private String address;

    private Date createdAt;

    private String salt;

}

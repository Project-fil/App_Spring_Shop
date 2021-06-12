package com.github.ratel.dto;

import com.github.ratel.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class UserRegDto {

    private String firstname;

    private String lastname;

    private String email;

    private String username;

    private String password;

    private String phone;

    private String address;

    private Date createdAt;

    private String salt;

    private String hashpassword;

    public UserRegDto(String firstname,
                      String lastname,
                      String email,
                      String username,
                      String password,
                      String phone,
                      String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public UserRegDto(User user) {
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }
}

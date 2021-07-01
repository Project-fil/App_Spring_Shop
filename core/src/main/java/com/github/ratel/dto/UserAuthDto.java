package com.github.ratel.dto;

import com.github.ratel.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDto {

    @NotEmpty
    @Size(min = 2, max = 20)
    private String login;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String password;

}

package com.example.ratel.entity;

import com.example.ratel.payload.Role;
import com.example.ratel.payload.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long user_id;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Column(name = "username", nullable = false, columnDefinition = "TEXT", unique = true)
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "phone", nullable = false, columnDefinition = "TEXT")
    private String phone;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "createdAt", nullable = false, columnDefinition = "TEXT")
    private Date createdAt;

    @Column(name = "updatedAt", columnDefinition = "TEXT")
    private Date updatedAt;

    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    private Role role;

    @Column(name = "status", nullable = false, columnDefinition = "TEXT")
    private UserStatus status;

}

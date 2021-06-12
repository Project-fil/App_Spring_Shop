package com.github.ratel.entity;

import com.github.ratel.payload.Role;
import com.github.ratel.payload.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long user_id;

    @Column(name = "firstname", nullable = false, columnDefinition = "TEXT")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "TEXT")
    private String lastname;

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

    @Column(name = "verification", nullable = false, columnDefinition = "TEXT")
    private UserVerificationStatus verification;

}

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
    private long userId;

    @Column(name = "firstname", nullable = false, columnDefinition = "TEXT")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "TEXT")
    private String lastname;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Column(name = "login", nullable = false, columnDefinition = "TEXT", unique = true)
    private String login;

    @Column(name = "hash_password", nullable = false, columnDefinition = "TEXT")
    private String hashPassword;

    @Column(name = "phone", nullable = false, columnDefinition = "TEXT")
    private String phone;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
//    @Column(name = "role", nullable = false)
    private RoleEntity role;

    @Column(name = "salt", nullable = false, columnDefinition = "TEXT")
    private String salt;

    @Column(name = "verification", nullable = false, columnDefinition = "TEXT")
    private UserVerificationStatus verification;

    public User(String login, String hashPassword) {
        this.login = login;
        this.hashPassword = hashPassword;
    }

    public User(
            String firstname,
            String lastname,
            String email,
            String login,
            String hashPassword,
            String phone,
            String address,
            Date createdAt,
            String salt
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.hashPassword = hashPassword;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.salt = salt;
    }
}

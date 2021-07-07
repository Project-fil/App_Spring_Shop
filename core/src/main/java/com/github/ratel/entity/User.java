package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import com.github.ratel.payload.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long id;

    @Column(name = "firstname", nullable = false, columnDefinition = "TEXT")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "TEXT")
    private String lastname;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Column(name = "login", nullable = false, columnDefinition = "TEXT", unique = true)
    private String login;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "phone", nullable = false, columnDefinition = "TEXT")
    private String phone;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    @Column(name = "verification", nullable = false, columnDefinition = "TEXT")
    private UserVerificationStatus verification;

    @NotNull
    @Column(name = "status")
    private EntityStatus status;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, UserVerificationStatus verification) {
        this.login = login;
        this.password = password;
        this.verification = verification;
    }

    public User(
            String firstname,
            String lastname,
            String email,
            String login,
            String password,
            String phone,
            String address,
            Date createdAt,
            UserVerificationStatus verification,
            EntityStatus status
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.verification = verification;
        this.status = status;
    }

    public User newPass(String password) {
        this.password = password;
        return this;
    }

    public User verificUser(UserVerificationStatus verification) {
        this.verification = verification;
        return this;
    }
}

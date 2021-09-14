package com.github.ratel.entity;

import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.UserVerificationStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "phone", columnDefinition = "TEXT")
    private String phone;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    private Date updatedAt;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "verification", nullable = false, columnDefinition = "TEXT")
    private UserVerificationStatus verification;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, UserVerificationStatus verification) {
        this.email = email;
        this.password = password;
        this.verification = verification;
    }

    public User(
            String firstname,
            String lastname,
            String email,
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

    public User verificationUser(UserVerificationStatus verification) {
        this.verification = verification;
        return this;
    }
}
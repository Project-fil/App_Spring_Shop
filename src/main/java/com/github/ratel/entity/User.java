package com.github.ratel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ratel.entity.enums.EntityStatus;
import com.github.ratel.entity.enums.Roles;
import com.github.ratel.entity.enums.UserVerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
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

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "phone", columnDefinition = "TEXT")
    private String phone;

//    @Column(name = "address", columnDefinition = "TEXT")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    private Roles roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification", nullable = false, columnDefinition = "TEXT")
    private UserVerificationStatus verification;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @JsonIgnore
    @CreatedDate
    @Column(name = "createdDate", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "lastModifiedDate", columnDefinition = "TIMESTAMP")
    private Date lastModifiedDate;

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
            Address address,
            Date createdDate,
            UserVerificationStatus verification,
            EntityStatus status
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.createdDate = createdDate;
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
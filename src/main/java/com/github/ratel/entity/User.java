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
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7990653085947378299L;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
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
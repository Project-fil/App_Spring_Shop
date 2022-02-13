package com.github.ratel.entity;

import com.github.ratel.entity.enums.Roles;
import com.github.ratel.entity.enums.UserVerificationStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Transient
    private static final long serialVersionUID = 7990653085947378299L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false, columnDefinition = "TEXT")
    private String firstname;

    @Column(name = "lastname", nullable = false, columnDefinition = "TEXT")
    private String lastname;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "file_id")
    private FileEntity fileEntity;

    @ManyToOne()
    @JoinColumn(name = "address_id")
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<Comment> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, columnDefinition = "TEXT")
    private Roles roles;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification", nullable = false, columnDefinition = "TEXT")
    private UserVerificationStatus verification;

    @Column(name = "removed")
    private boolean removed;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date cratedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

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
            Address address,
            UserVerificationStatus verification,
            boolean removed
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.verification = verification;
        this.removed = removed;
    }

    public User newPass(String password) {
        this.password = password;
        return this;
    }

    public User verificationUser(UserVerificationStatus verification) {
        this.verification = verification;
        return this;
    }

    public void addOrder(Order order) {
        if (this.orders.isEmpty())
            this.orders = new ArrayList<>();
        this.orders.add(order);
    }

}
package com.github.ratel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "user_addres")
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    @Transient
    private static final long serialVersionUID = -3314738285065551787L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "phone", columnDefinition = "TEXT")
    private String phone;

    @Column(name = "country", columnDefinition = "TEXT")
    private String country;

    @Column(name = "city", columnDefinition = "TEXT")
    private String city;

    @Column(name = "street", columnDefinition = "TEXT")
    private String street;

    @Column(name = "houseNumber", columnDefinition = "INT")
    private int houseNumber;

    @Column(name = "apartmentNumber", columnDefinition = "INT")
    private int apartmentNumber;

    @ToString.Exclude
    @OneToMany(mappedBy = "address")
    private List<User> userAddress = new ArrayList<>();

    @Column(name = "removed")
    private boolean removed;

    @JsonIgnore
    @CreatedDate
    @Column(name = "createdDate", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "lastModifiedDate", columnDefinition = "TIMESTAMP")
    private Date lastModifiedDate;

    public void addUser(User user) {
        if (Objects.isNull(this.userAddress))
            this.userAddress = new ArrayList<>();
        this.userAddress.add(user);
    }

}

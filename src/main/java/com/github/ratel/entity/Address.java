package com.github.ratel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user_addres")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long id;

    @Column(name = "country", nullable = false, columnDefinition = "TEXT")
    private String country;

    @Column(name = "city", nullable = false, columnDefinition = "TEXT")
    private String city;

    @Column(name = "street", nullable = false, columnDefinition = "TEXT")
    private String street;

    @Column(name = "houseNumber", nullable = false, columnDefinition = "INT")
    private int houseNumber;

    @Column(name = "apartmentNumber", columnDefinition = "INT")
    private int apartmentNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private List<User> userAddress = new ArrayList<>();

    @JsonIgnore
    @CreatedDate
    @Column(name = "createdDate", columnDefinition = "TIMESTAMP")
    private Date createdDate;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "lastModifiedDate", columnDefinition = "TIMESTAMP")
    private Date lastModifiedDate;

}

package com.github.ratel.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private String id;

    private String country;

    private String city;

    private String street;

    private int houseNumber;

    private int apartmentNumber;

    private List<UserResponse> userAddress = new ArrayList<>();

    private Date createdDate;

    private Date lastModifiedDate;

}

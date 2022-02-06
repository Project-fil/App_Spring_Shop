package com.github.ratel.services;

import com.github.ratel.entity.Address;
import org.springframework.stereotype.Service;

public interface AddressService {

    Address findById(Long id);

    Address findByPhone(String phone);

    Address save(Address address);

    Address update(Address address);

    void delete(Long id);

}

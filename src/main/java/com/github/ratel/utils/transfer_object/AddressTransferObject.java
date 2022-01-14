package com.github.ratel.utils.transfer_object;

import com.github.ratel.entity.Address;
import com.github.ratel.payload.response.AddressResponse;
import lombok.experimental.UtilityClass;

import java.util.Objects;
import java.util.stream.Collectors;

@UtilityClass
public class AddressTransferObject {

    public static AddressResponse fromAddress(Address payload) {
        if (Objects.isNull(payload)) {
            return null;
        }
        return new AddressResponse(
                payload.getId(),
                payload.getCountry(),
                payload.getCity(),
                payload.getStreet(),
                payload.getHouseNumber(),
                payload.getApartmentNumber(),
                payload.getUserAddress().stream().map(UserTransferObject::fromUser).collect(Collectors.toList()),
                payload.getCreatedDate(),
                payload.getLastModifiedDate()
        );
    }

}

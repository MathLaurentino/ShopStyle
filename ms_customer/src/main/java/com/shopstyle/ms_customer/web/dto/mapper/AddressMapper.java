package com.shopstyle.ms_customer.web.dto.mapper;

import com.shopstyle.ms_customer.entity.Address;
import com.shopstyle.ms_customer.web.dto.*;
import org.modelmapper.ModelMapper;

public class AddressMapper {

    public static Address toAddress(AddressPostDto dto) {
        return new ModelMapper().map(dto, Address.class);
    }

    public static Address toAddress(AddressPutDto dto) {
        return new ModelMapper().map(dto, Address.class);
    }

    public static AddressGetDto toDto(Address address) {
        AddressGetDto dto = new AddressGetDto();
        dto.setId(address.getId());
        dto.setState(address.getState());
        dto.setCity(address.getCity());
        dto.setDistrict(address.getDistrict());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        dto.setCep(address.getCep());
        dto.setComplement(address.getComplement());
        dto.setCustomer(address.getCustomer().getId());

        return dto;
    }

}

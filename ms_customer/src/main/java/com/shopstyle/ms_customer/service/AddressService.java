package com.shopstyle.ms_customer.service;

import com.shopstyle.ms_customer.web.dto.*;

public interface AddressService {

    AddressGetDto createAddress(AddressPostDto dto);

    AddressGetDto updateAddress(Long id, AddressPutDto dto);

    void deleteAddress(Long id);

}

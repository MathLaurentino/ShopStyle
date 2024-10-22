package com.shopstyle.ms_customer.service;

import com.shopstyle.ms_customer.web.dto.*;

public interface AddressService {

    AddressGetDto createAddress(AddressPostAndPutDto dto);

    AddressGetDto updateAddress(Long id, AddressPostAndPutDto dto);

    void deleteAddress(Long id);

}

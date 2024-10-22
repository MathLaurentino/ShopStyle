package com.shopstyle.ms_customer.web.controller;

import com.shopstyle.ms_customer.web.dto.*;
import org.springframework.http.ResponseEntity;

public interface AddressController {

    ResponseEntity<AddressGetDto> createAddress(AddressPostAndPutDto dto);

    ResponseEntity<AddressGetDto> updateAddress(Long id, AddressPostAndPutDto dto);

    ResponseEntity<Void> deleteAddress(Long id);

}

package com.shopstyle.ms_customer.web.controller;

import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import org.springframework.http.ResponseEntity;

public interface CustomerController {

    ResponseEntity<CustomerGetDto> createCustomer(CustomerPostDto dto);

    ResponseEntity<CustomerGetDto> getCustomerById(Long id);

    ResponseEntity<CustomerGetDto> updateCustomer(Long id, CustomerPutDto dto);

    ResponseEntity<Void> updatePassword(Long id, String password);

}

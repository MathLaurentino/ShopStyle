package com.shopstyle.ms_customer.web.controller.impl;

import com.shopstyle.ms_customer.service.CustomerService;
import com.shopstyle.ms_customer.web.controller.CustomerController;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/customers")
@RestController
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService service;

    @Override
    @PostMapping
    public ResponseEntity<CustomerGetDto> createCustomer(@Valid @RequestBody CustomerPostDto dto) {
        var customerGetDto = service.createCustomer(dto);
        return ResponseEntity.status(201).body(customerGetDto);
    }

    //TODO
    @Override
    public ResponseEntity<CustomerGetDto> getCustomerById(Long id) {
        return null;
    }

    //TODO
    @Override
    public ResponseEntity<CustomerGetDto> updateCustomer(Long id, CustomerPutDto dto) {
        return null;
    }

    //TODO
    @Override
    public ResponseEntity<Void> updatePassword(Long id, String password) {
        return null;
    }

}

package com.shopstyle.ms_customer.web.controller.impl;

import com.shopstyle.ms_customer.service.CustomerService;
import com.shopstyle.ms_customer.web.controller.CustomerController;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(customerGetDto);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CustomerGetDto> getCustomerById(@PathVariable Long id) {
        var customerGetDto = service.getCustomerById(id);
        return ResponseEntity.ok(customerGetDto);
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

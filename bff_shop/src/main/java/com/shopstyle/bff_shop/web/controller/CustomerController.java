package com.shopstyle.bff_shop.web.controller;

import com.shopstyle.bff_shop.service.CustomerService;
import com.shopstyle.bff_shop.web.dto.ms_customer.CustomerGetDto;
import com.shopstyle.bff_shop.web.dto.ms_customer.CustomerPostDto;
import com.shopstyle.bff_shop.web.dto.ms_customer.CustomerPutDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerGetDto> createCustomer(@RequestBody CustomerPostDto dto) {
        return service.createCustomer(dto);
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerGetDto> getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerGetDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerPutDto dto) {
        return service.updateCustomer(id, dto);
    }

}

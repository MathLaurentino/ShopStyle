package com.shopstyle.bff_shop.web.controller;

import com.shopstyle.bff_shop.service.CustomerService;
import com.shopstyle.bff_shop.web.dto.ms_customer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class CustomerController {

    private final CustomerService service;

    @PostMapping("/customers")
    public ResponseEntity<CustomerGetDto> createCustomer(@RequestBody CustomerPostDto dto) {
        return service.createCustomer(dto);
    }

    @GetMapping("/customers/{id}")
    ResponseEntity<CustomerGetDto> getCustomerById(@PathVariable Long id) {
        return service.getCustomerById(id);
    }

    @PutMapping("/customers/{id}")
    ResponseEntity<CustomerGetDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerPutDto dto) {
        return service.updateCustomer(id, dto);
    }

    @PostMapping("/address")
    ResponseEntity<AddressGetDto> createAddress(@RequestBody AddressPostDto dto){
        return service.createAddress(dto);
    }

    @PutMapping("/address/{id}")
    ResponseEntity<AddressGetDto> updateAddress(@PathVariable Long id, @RequestBody AddressPutDto dto){
        return service.updateAddress(id, dto);
    }

    @DeleteMapping("/address/{id}")
    ResponseEntity<Void> deleteAddress(@PathVariable Long id){
        return service.deleteAddress(id);
    }

}

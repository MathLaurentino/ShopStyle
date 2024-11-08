package com.shopstyle.bff_shop.service;

import com.shopstyle.bff_shop.web.dto.ms_customer.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-customer", path = "/v1")
public interface CustomerService {

    @PostMapping("/customers")
    ResponseEntity<CustomerGetDto> createCustomer(@RequestBody CustomerPostDto dto);

    @GetMapping("/customers/{id}")
    ResponseEntity<CustomerGetDto> getCustomerById(@PathVariable Long id);

    @PutMapping("/customers/{id}")
    ResponseEntity<CustomerGetDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerPutDto dto);

//    ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody String password);


    @PostMapping("/address")
    ResponseEntity<AddressGetDto> createAddress(@RequestBody AddressPostDto dto);

    @PutMapping("/address/{id}")
    ResponseEntity<AddressGetDto> updateAddress(@PathVariable Long id, @RequestBody AddressPutDto dto);

    @DeleteMapping("/address/{id}")
    ResponseEntity<Void> deleteAddress(@PathVariable Long id);


    @PostMapping("/login")
    ResponseEntity<JwtToken> autenticar(@RequestBody CustomerLoginDto dto);
}

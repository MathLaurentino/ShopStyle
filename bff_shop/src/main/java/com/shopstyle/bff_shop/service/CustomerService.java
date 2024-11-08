package com.shopstyle.bff_shop.service;

import com.shopstyle.bff_shop.web.dto.ms_customer.CustomerGetDto;
import com.shopstyle.bff_shop.web.dto.ms_customer.CustomerPostDto;
import com.shopstyle.bff_shop.web.dto.ms_customer.CustomerPutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "ms-customer", path = "/v1/customers")
public interface CustomerService {

    @PostMapping
    ResponseEntity<CustomerGetDto> createCustomer(@RequestBody CustomerPostDto dto);

    @GetMapping("/{id}")
    ResponseEntity<CustomerGetDto> getCustomerById(@PathVariable Long id);

    @PutMapping("/{id}")
    ResponseEntity<CustomerGetDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerPutDto dto);

//    ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody String password);
}

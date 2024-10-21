package com.shopstyle.ms_customer.service;

import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;

public interface CustomerService {

    CustomerGetDto createCustomer(CustomerPostDto dto);

    CustomerGetDto getCustomerById(Long id);

    CustomerGetDto updateCustomer(Long id, CustomerPutDto dto);

    void updatePassword(Long id, String password);
}

package com.shopstyle.ms_customer.web.dto.mapper;

import com.shopstyle.ms_customer.entity.Customer;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import org.modelmapper.ModelMapper;

public class CustomerMapper {

    public static Customer toCustomer(CustomerPostDto dto) {
        return new ModelMapper().map(dto, Customer.class);
    }

    public static Customer toCustomer(CustomerPutDto dto) {
        return new ModelMapper().map(dto, Customer.class);
    }

    public static CustomerGetDto toDto(Customer customer) {
        return new ModelMapper().map(customer, CustomerGetDto.class);
    }

}

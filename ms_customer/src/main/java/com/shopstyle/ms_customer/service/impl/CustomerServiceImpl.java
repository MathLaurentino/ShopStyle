package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.entity.Customer;
import com.shopstyle.ms_customer.exception.CpfAlreadyInUseException;
import com.shopstyle.ms_customer.exception.EmailAlreadyInUseException;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import com.shopstyle.ms_customer.service.CustomerService;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import com.shopstyle.ms_customer.web.dto.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public CustomerGetDto createCustomer(CustomerPostDto dto) {
        Customer customer = CustomerMapper.toCustomer(dto);

        repository.findByCpf(customer.getCpf()).ifPresent(s -> {
            throw new CpfAlreadyInUseException("Cpf already in use");
        });

        repository.findByEmail(customer.getEmail()).ifPresent(s -> {
            throw new EmailAlreadyInUseException("Email already in use");
        });

        return CustomerMapper.toDto(repository.save(customer));
    }

    //TODO
    @Override
    public CustomerGetDto getCustomerById(Long id) {
        return null;
    }

    //TODO
    @Override
    public CustomerGetDto updateCustomer(Long id, CustomerPutDto dto) {
        return null;
    }

    //TODO
    @Override
    public void updatePassword(Long id, String password) {

    }

}

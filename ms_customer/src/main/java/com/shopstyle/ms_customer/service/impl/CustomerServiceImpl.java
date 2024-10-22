package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.entity.Customer;
import com.shopstyle.ms_customer.entity.enums.Sex;
import com.shopstyle.ms_customer.exception.CpfAlreadyInUseException;
import com.shopstyle.ms_customer.exception.EmailAlreadyInUseException;
import com.shopstyle.ms_customer.exception.EntityNotFoundException;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import com.shopstyle.ms_customer.service.CustomerService;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPostDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import com.shopstyle.ms_customer.web.dto.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    @Transactional(readOnly = true)
    public CustomerGetDto getCustomerById(Long id) {
        var customer = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer not found"));
        return CustomerMapper.toDto(customer);
    }


    @Override
    public CustomerGetDto updateCustomer(Long id, CustomerPutDto dto) {
        var customer = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer not found"));

        checkDuplicateCpfAndEmail(dto, customer.getId());

        customer.setCpf(dto.getCpf());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setSex(Sex.valueOf(dto.getSex()));
        customer.setBirthdate(dto.getBirthdate());
        customer.setEmail(dto.getEmail());
        customer.setActive(dto.getActive().equals("true"));

        return CustomerMapper.toDto(repository.save(customer));
    }

    //TODO
    @Override
    public void updatePassword(Long id, String password) {

    }


    private void checkDuplicateCpfAndEmail(CustomerPutDto dto, Long customerId) {
        repository.findByCpf(dto.getCpf()).filter(existingCustomer -> !existingCustomer.getId().equals(customerId))
                .ifPresent(s -> { throw new CpfAlreadyInUseException("Cpf already in use"); });

        repository.findByEmail(dto.getEmail()).filter(existingCustomer -> !existingCustomer.getId().equals(customerId))
                .ifPresent(s -> { throw new EmailAlreadyInUseException("Email already in use"); });
    }

}

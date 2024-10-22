package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.exception.EntityNotFoundException;
import com.shopstyle.ms_customer.repository.AddressRepository;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import com.shopstyle.ms_customer.service.AddressService;
import com.shopstyle.ms_customer.web.dto.AddressGetDto;
import com.shopstyle.ms_customer.web.dto.AddressPostAndPutDto;
import com.shopstyle.ms_customer.web.dto.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AddressGetDto createAddress(AddressPostAndPutDto dto) {
        var customer = customerRepository.findById(dto.getCustomer()).orElseThrow(
                () -> new EntityNotFoundException("Customer not found"));

        var address = AddressMapper.toAddress(dto);
        address.setCustomer(customer);

        return AddressMapper.toDto(addressRepository.save(address));
    }

    //TODO
    @Override
    public AddressGetDto updateAddress(Long id, AddressPostAndPutDto dto) {
        return null;
    }

    //TODO
    @Override
    public void deleteAddress(Long id) {

    }
}

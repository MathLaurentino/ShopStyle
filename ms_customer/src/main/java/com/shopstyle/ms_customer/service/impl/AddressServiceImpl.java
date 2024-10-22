package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.exception.EntityNotFoundException;
import com.shopstyle.ms_customer.repository.AddressRepository;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import com.shopstyle.ms_customer.service.AddressService;
import com.shopstyle.ms_customer.web.dto.AddressGetDto;
import com.shopstyle.ms_customer.web.dto.AddressPostDto;
import com.shopstyle.ms_customer.web.dto.AddressPutDto;
import com.shopstyle.ms_customer.web.dto.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    @Override
    public AddressGetDto createAddress(AddressPostDto dto) {
        var customer = customerRepository.findById(dto.getCustomer()).orElseThrow(
                () -> new EntityNotFoundException("Customer not found"));

        var address = AddressMapper.toAddress(dto);
        address.setCustomer(customer);

        return AddressMapper.toDto(addressRepository.save(address));
    }

    @Override
    public AddressGetDto updateAddress(Long id, AddressPutDto dto) {
        var address = addressRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Address not found"));

        address.setState(dto.getState());
        address.setCity(dto.getCity());
        address.setDistrict(dto.getDistrict());
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        address.setCep(dto.getCep());
        address.setComplement(dto.getComplement());

        return AddressMapper.toDto(addressRepository.save(address));
    }

    //TODO
    @Override
    public void deleteAddress(Long id) {

    }
}

package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.exception.EntityNotFoundException;
import com.shopstyle.ms_customer.repository.AddressRepository;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.shopstyle.ms_customer.builders.AddressBuilder.anAddress;
import static com.shopstyle.ms_customer.builders.AddressPostDtoBuilder.anAddressPostDto;
import static com.shopstyle.ms_customer.builders.AddressPutDtoBuilder.anAddressPutDto;
import static com.shopstyle.ms_customer.builders.CustomerBuilder.aCustomer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AddressServiceImpl service;

    @Test
    void createAddress_Success() {
        Long customerId = 1L;
        var postDto = anAddressPostDto().withCustomer(customerId).get();
        var customer = aCustomer().withId(customerId).get();
        var address = anAddress().withCustomer(customer).get();
        when(customerRepository.findById(postDto.getCustomer())).thenReturn(Optional.of(customer));
        when(addressRepository.save(address)).thenReturn(address);

        var result = service.createAddress(postDto);

        assertNotNull(result);
        assertEquals(postDto.getCep(), result.getCep());
        verify(addressRepository).save(address);
    }

    @Test
    void createAddress_EntityNotFoundException() {
        Long customerId = 1L;
        var postDto = anAddressPostDto().withCustomer(customerId).get();
        when(customerRepository.findById(postDto.getCustomer())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.createAddress(postDto));
    }

    @Test
    void updateAddress_Success() {
        Long addressId = 1L;
        var dto = anAddressPutDto().get();
        var existingAddress = anAddress().withId(addressId).get();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));
        when(addressRepository.save(existingAddress)).thenReturn(existingAddress);

        var result = service.updateAddress(addressId, dto);

        assertNotNull(result);
        assertEquals(dto.getCep(), result.getCep());
        assertEquals(dto.getCity(), result.getCity());
        verify(addressRepository).save(existingAddress);
    }

    @Test
    void updateAddress_EntityNotFoundException() {
        Long addressId = 1L;
        var dto = anAddressPutDto().get();
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateAddress(addressId, dto));
        verify(addressRepository, never()).save(any());
    }

    @Test
    void deleteAddress_Success() {
        Long addressId = 1L;
        var existingAddress = anAddress().withId(addressId).get();
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));

        service.deleteAddress(addressId);

        verify(addressRepository).deleteById(addressId);
    }

    @Test
    void deleteAddress_EntityNotFoundException() {
        Long addressId = 1L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.deleteAddress(addressId));
        verify(addressRepository, never()).deleteById(anyLong());
    }

}

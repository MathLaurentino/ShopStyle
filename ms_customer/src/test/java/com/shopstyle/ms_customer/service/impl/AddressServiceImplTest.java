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

import static com.shopstyle.ms_customer.builders.AddressPostAndPutDtoBuilder.anAddressPostAndPutDto;
import static com.shopstyle.ms_customer.builders.AddressBuilder.anAddress;
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
        var postDto = anAddressPostAndPutDto().withCustomer(customerId).get();
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
        var postDto = anAddressPostAndPutDto().withCustomer(customerId).get();

        when(customerRepository.findById(postDto.getCustomer())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.createAddress(postDto));
    }

}

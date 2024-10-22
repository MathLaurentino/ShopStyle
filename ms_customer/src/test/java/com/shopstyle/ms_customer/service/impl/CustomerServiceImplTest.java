package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.builders.CustomerPutBuilder;
import com.shopstyle.ms_customer.entity.Customer;
import com.shopstyle.ms_customer.exception.CpfAlreadyInUseException;
import com.shopstyle.ms_customer.exception.EmailAlreadyInUseException;
import com.shopstyle.ms_customer.exception.EntityNotFoundException;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
import com.shopstyle.ms_customer.web.dto.CustomerPutDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.shopstyle.ms_customer.builders.CustomerBuilder.aCustomer;
import static com.shopstyle.ms_customer.builders.CustomerPostBuilder.aCustomerPostDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    void createCustomer_Success() {
        var postDto = aCustomerPostDto().get();
        var customer = aCustomer().get();

        when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
        when(repository.findByEmail(customer.getEmail())).thenReturn(Optional.empty());
        when(repository.save(customer)).thenReturn(customer);

        CustomerGetDto result = service.createCustomer(postDto);

        assertNotNull(result);
        assertEquals(postDto.getCpf(), result.getCpf());
        verify(repository).save(customer);
    }

    @Test
    void createCustomer_CpfAlreadyInUseException() {
        var postDto = aCustomerPostDto().get();
        var customer = aCustomer().get();

        when(repository.findByCpf(postDto.getCpf())).thenReturn(Optional.of(customer));

        assertThrows(CpfAlreadyInUseException.class, () -> service.createCustomer(postDto));

        verify(repository, times(1)).findByCpf(postDto.getCpf());
    }

    @Test
    void createCustomer_ThrowEmailAlreadyInUseException() {
        var postDto = aCustomerPostDto().get();
        var customer = aCustomer().get();

        when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
        when(repository.findByEmail(postDto.getEmail())).thenReturn(Optional.of(customer));

        assertThrows(EmailAlreadyInUseException.class, () -> service.createCustomer(postDto));

        verify(repository, times(1)).findByCpf(postDto.getCpf());
    }

    @Test
    void getCustomer_Success() {
        var customer = aCustomer().withId(1L).get();
        when(repository.findById(customer.getId())).thenReturn(Optional.of(customer));

        CustomerGetDto result = service.getCustomerById(customer.getId());

        assertEquals(customer.getId(), result.getId());
        verify(repository, times(1)).findById(customer.getId());
    }

    @Test
    void getCustomer_ThrowEntityNotFoundException() {

        when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.getCustomerById(any()));

        verify(repository, times(1)).findById(any());
    }

    @Test
    void shouldUpdateCustomerSuccessfully() {
        Long customerId = 1L;
        CustomerPutDto dto = CustomerPutBuilder.aCustomerPutDto()
                .withFirsName("Jane").get();
        Customer existingCustomer = aCustomer()
                .withId(customerId).get();

        when(repository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.of(existingCustomer));
        when(repository.findByEmail(dto.getEmail())).thenReturn(Optional.of(existingCustomer));
        when(repository.save(any(Customer.class))).thenReturn(existingCustomer);

        CustomerGetDto result = service.updateCustomer(customerId, dto);

        assertEquals("Jane", result.getFirstName());
        verify(repository).findById(customerId);
        verify(repository).save(existingCustomer);
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenCustomerDoesNotExist() {
        Long customerId = 1L;
        CustomerPutDto dto = CustomerPutBuilder.aCustomerPutDto().get();

        when(repository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateCustomer(customerId, dto));
    }

    @Test
    void shouldThrowCpfAlreadyInUseExceptionWhenCpfIsDuplicated() {
        CustomerPutDto dto = CustomerPutBuilder.aCustomerPutDto()
                .withCpf("987.654.321-00")
                .get();
        Long customerId = 1L;
        Customer existingCustomer = aCustomer()
                .withId(customerId)
                .withCpf("123.456.789-00")
                .get();
        Customer anotherCustomer = aCustomer()
                .withId(2L)
                .withCpf("987.654.321-00")
                .get();

        when(repository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.of(anotherCustomer));

        assertThrows(CpfAlreadyInUseException.class, () -> service.updateCustomer(customerId, dto));
    }

    @Test
    void shouldThrowEmailAlreadyInUseExceptionWhenEmailIsDuplicated() {
        Long customerId = 1L;
        CustomerPutDto dto = CustomerPutBuilder.aCustomerPutDto()
                .withEmail("jane.doe@example.com")
                .get();
        Customer existingCustomer = aCustomer()
                .withId(customerId)
                .withEmail("customer@customer.com")
                .get();
        Customer anotherCustomer = aCustomer()
                .withId(2L)
                .withEmail("jane.doe@example.com")
                .get();

        when(repository.findById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(repository.findByCpf(dto.getCpf())).thenReturn(Optional.of(existingCustomer));
        when(repository.findByEmail(dto.getEmail())).thenReturn(Optional.of(anotherCustomer));

        assertThrows(EmailAlreadyInUseException.class, () -> service.updateCustomer(customerId, dto));
    }


}

package com.shopstyle.ms_customer.service.impl;

import com.shopstyle.ms_customer.exception.CpfAlreadyInUseException;
import com.shopstyle.ms_customer.exception.EmailAlreadyInUseException;
import com.shopstyle.ms_customer.repository.CustomerRepository;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;
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
    private CustomerServiceImpl customerService;

    @Test
    void createCustomer_Success() {
        var postDto = aCustomerPostDto().get();
        var customer = aCustomer().get();

        when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
        when(repository.findByEmail(customer.getEmail())).thenReturn(Optional.empty());
        when(repository.save(customer)).thenReturn(customer);

        CustomerGetDto result = customerService.createCustomer(postDto);

        assertNotNull(result);
        assertEquals(postDto.getCpf(), result.getCpf());
        verify(repository).save(customer);
    }

    @Test
    void createCustomer_CpfAlreadyInUseException() {
        var postDto = aCustomerPostDto().get();
        var customer = aCustomer().get();

        when(repository.findByCpf(postDto.getCpf())).thenReturn(Optional.of(customer));

        assertThrows(CpfAlreadyInUseException.class, () -> customerService.createCustomer(postDto));

        verify(repository, times(1)).findByCpf(postDto.getCpf());
    }

    @Test
    void createCustomer_ThrowEmailAlreadyInUseException() {
        var postDto = aCustomerPostDto().get();
        var customer = aCustomer().get();

        when(repository.findByCpf(customer.getCpf())).thenReturn(Optional.empty());
        when(repository.findByEmail(postDto.getEmail())).thenReturn(Optional.of(customer));

        assertThrows(EmailAlreadyInUseException.class, () -> customerService.createCustomer(postDto));

        verify(repository, times(1)).findByCpf(postDto.getCpf());
    }

}

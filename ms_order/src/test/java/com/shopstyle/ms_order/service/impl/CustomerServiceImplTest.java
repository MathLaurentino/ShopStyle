package com.shopstyle.ms_order.service.impl;

import com.shopstyle.ms_order.exception.CustomerInactiveException;
import com.shopstyle.ms_order.exception.EntityNotFoundException;
import com.shopstyle.ms_order.exception.ErrorMicroServiceComunicationException;
import com.shopstyle.ms_order.exception.InvalidAddressException;
import com.shopstyle.ms_order.feign.CustomerFeignService;
import com.shopstyle.ms_order.servece.impl.CustomerServiceImpl;
import com.shopstyle.ms_order.web.dto.CustomerDto;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import com.shopstyle.ms_order.web.dto.feign.AddressDtoFeign;
import com.shopstyle.ms_order.web.dto.feign.CustomerDtoFeign;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerFeignService customerFeignService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testCustomerDataIsValid_Success() {
        OrderReqDto dto = new OrderReqDto();
        dto.setCustomer(new CustomerDto(1L, 123L));
        dto.getCustomer().setAddressId(123L);

        CustomerDtoFeign customerDto = new CustomerDtoFeign();
        customerDto.setActive(true);
        AddressDtoFeign address = new AddressDtoFeign();
        address.setId(123L);
        customerDto.setAddresses(Set.of(address));

        when(customerFeignService.findCustomerById(dto.getCustomer().getId())).thenReturn(ResponseEntity.ok(customerDto));

        Boolean result = customerService.customerDataIsValid(dto);
        assertTrue(result);
        verify(customerFeignService).findCustomerById(dto.getCustomer().getId());
    }

    @Test
    void testCustomerDataIsValid_CustomerInactive() {
        OrderReqDto dto = new OrderReqDto();
        dto.setCustomer(new CustomerDto(1L, 123L));
        dto.getCustomer().setAddressId(123L);

        CustomerDtoFeign customerDto = new CustomerDtoFeign();
        customerDto.setActive(false);
        AddressDtoFeign address = new AddressDtoFeign();
        address.setId(123L);
        customerDto.setAddresses(Set.of(address));

        when(customerFeignService.findCustomerById(dto.getCustomer().getId())).thenReturn(ResponseEntity.ok(customerDto));

        assertThrows(CustomerInactiveException.class, () -> {
            customerService.customerDataIsValid(dto);
        });
        verify(customerFeignService).findCustomerById(dto.getCustomer().getId());
    }

    @Test
    void testCustomerDataIsValid_InvalidAddress() {
        OrderReqDto dto = new OrderReqDto();
        dto.setCustomer(new CustomerDto(1L, 123L));
        dto.getCustomer().setAddressId(999L);

        CustomerDtoFeign customerDto = new CustomerDtoFeign();
        customerDto.setActive(true);
        AddressDtoFeign address = new AddressDtoFeign();
        address.setId(123L);
        customerDto.setAddresses(Set.of(address));

        when(customerFeignService.findCustomerById(dto.getCustomer().getId())).thenReturn(ResponseEntity.ok(customerDto));

        assertThrows(InvalidAddressException.class, () -> {
            customerService.customerDataIsValid(dto);
        });
        verify(customerFeignService).findCustomerById(dto.getCustomer().getId());
    }

}

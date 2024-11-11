package com.shopstyle.ms_order.service.impl;

import com.shopstyle.ms_order.entity.CartItem;
import com.shopstyle.ms_order.entity.Customer;
import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.Payment;
import com.shopstyle.ms_order.entity.enums.OrderStatus;
import com.shopstyle.ms_order.kafka.CatalogSkusProducerService;
import com.shopstyle.ms_order.kafka.OrderPaymentProducerService;
import com.shopstyle.ms_order.repository.OrderRepository;
import com.shopstyle.ms_order.servece.CustomerService;
import com.shopstyle.ms_order.servece.SkuService;
import com.shopstyle.ms_order.servece.impl.OrderServiceImpl;
import com.shopstyle.ms_order.web.dto.*;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;
import com.shopstyle.ms_order.web.dto.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository repository;
    @Mock
    private CustomerService customerService;
    @Mock
    private SkuService skuService;
    @Mock
    private OrderPaymentProducerService orderPaymentService;
    @Mock
    private CatalogSkusProducerService catalogSkusProducerService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testCreateOrder_Success() {
        OrderReqDto dto = new OrderReqDto();
        dto.setCart(Collections.singletonList(new CartItemDto(1L, 2)));
        dto.setCustomer(new CustomerDto(1L, 123L));
        dto.setPayment(new PaymentDto(1L, 3));
        Order order = OrderMapper.toOrder(dto);
        order.setId("67322e3e636c1b1649ba3b27");
        order.setTotal(new BigDecimal("100.00"));
        order.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order.setDate(LocalDateTime.now());

        when(customerService.customerDataIsValid(dto)).thenReturn(true);
        when(skuService.cartTotalPrice(dto.getCart())).thenReturn(new BigDecimal("100.00"));
        when(repository.save(any(Order.class))).thenReturn(order);

        CreatedOrderDto result = orderService.createOrder(dto);
        assertEquals("67322e3e636c1b1649ba3b27", result.getId(), "Order ID should match");
        assertEquals(OrderStatus.PROCESSING_PAYMENT, result.getStatus(), "Order status should be processing payment");
        assertEquals(new BigDecimal("100.00"), result.getTotal(), "Order total should match");

        verify(customerService).customerDataIsValid(dto);
        verify(skuService).cartTotalPrice(dto.getCart());
        verify(repository).save(any(Order.class));
        verify(orderPaymentService).sendOrderPaymentMessage(any(OrderPaymentMessage.class));
    }

    @Test
    void testGetOrdersWithoutStatus() {
        GetOrderQueryParam queryParams = new GetOrderQueryParam();
        queryParams.setStartDate(LocalDateTime.now().minusDays(1));
        queryParams.setEndDate(LocalDateTime.now());

        Order order1 = new Order();
        order1.setId("1");
        order1.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order1.setDate(LocalDateTime.now());
        order1.setCustomer(new Customer(1L, 1L));
        order1.setPayment(new Payment(1L, 4));
        order1.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findByDateBetween(queryParams.getStartDate(), queryParams.getEndDate())).thenReturn(Arrays.asList(order1, order1));

        List<OrderGetDto> result = orderService.getOrders(queryParams);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository).findByDateBetween(queryParams.getStartDate(), queryParams.getEndDate());
    }

    @Test
    void testGetOrdersWithStatus() {
        GetOrderQueryParam queryParams = new GetOrderQueryParam();
        queryParams.setStartDate(LocalDateTime.now().minusDays(1));
        queryParams.setEndDate(LocalDateTime.now());
        queryParams.setStatus("PROCESSING_PAYMENT");
        Order order1 = new Order();
        order1.setId("1");
        order1.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order1.setDate(LocalDateTime.now());
        order1.setCustomer(new Customer(1L, 1L));
        order1.setPayment(new Payment(1L, 4));
        order1.setCart(List.of(new CartItem(1L, 2)));
        when(repository.findByDateBetweenAndStatus(queryParams.getStartDate(), queryParams.getEndDate(), OrderStatus.PROCESSING_PAYMENT)).thenReturn(Arrays.asList(order1, order1));

        List<OrderGetDto> result = orderService.getOrders(queryParams);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository).findByDateBetweenAndStatus(queryParams.getStartDate(), queryParams.getEndDate(), OrderStatus.PROCESSING_PAYMENT);
    }

}

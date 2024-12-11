package com.shopstyle.ms_order.service.impl;

import com.shopstyle.ms_order.entity.CartItem;
import com.shopstyle.ms_order.entity.Customer;
import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.Payment;
import com.shopstyle.ms_order.entity.enums.OrderStatus;
import com.shopstyle.ms_order.exception.EntityNotFoundException;
import com.shopstyle.ms_order.kafka.CatalogSkusProducerService;
import com.shopstyle.ms_order.kafka.OrderPaymentProducerService;
import com.shopstyle.ms_order.repository.OrderRepository;
import com.shopstyle.ms_order.servece.CustomerService;
import com.shopstyle.ms_order.servece.SkuService;
import com.shopstyle.ms_order.servece.impl.OrderServiceImpl;
import com.shopstyle.ms_order.web.dto.*;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;
import com.shopstyle.ms_order.web.dto.kafka.SkusMessage;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testGetOrdersByCustomerIdWithDateRange() {
        GetOrderByCustomerIdQueryParam queryParams = new GetOrderByCustomerIdQueryParam();
        queryParams.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        queryParams.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));

        Long customerId = 1L;

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findByCustomerIdAndDateBetween(customerId, queryParams.getStartDate(), queryParams.getEndDate()))
                .thenReturn(List.of(order));

        List<OrderGetDto> orders = orderService.getOrdersByCustomerId(queryParams, customerId);

        assertEquals(1, orders.size());
        verify(repository).findByCustomerIdAndDateBetween(customerId, queryParams.getStartDate(), queryParams.getEndDate());
    }

    @Test
    void testGetOrdersByCustomerIdWithStatus() {
        GetOrderByCustomerIdQueryParam queryParams = new GetOrderByCustomerIdQueryParam();
        queryParams.setStatus("PAYMENT_SUCCESSFUL");

        Long customerId = 1L;

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));
        when(repository.findByCustomerIdAndStatus(customerId, OrderStatus.PAYMENT_SUCCESSFUL))
                .thenReturn(List.of(order));

        List<OrderGetDto> orders = orderService.getOrdersByCustomerId(queryParams, customerId);

        assertEquals(1, orders.size());
        verify(repository).findByCustomerIdAndStatus(customerId, OrderStatus.PAYMENT_SUCCESSFUL);
    }

    @Test
    void testGetOrdersByCustomerIdWithNoFilters() {
        GetOrderByCustomerIdQueryParam queryParams = new GetOrderByCustomerIdQueryParam();

        Long customerId = 1L;

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findByCustomerId(customerId))
                .thenReturn(List.of(order));

        List<OrderGetDto> orders = orderService.getOrdersByCustomerId(queryParams, customerId);

        assertEquals(1, orders.size());
        verify(repository).findByCustomerId(customerId);
    }

    @Test
    void testGetOrdersByCustomerIdWithDateRangeAndStatus() {
        GetOrderByCustomerIdQueryParam queryParams = new GetOrderByCustomerIdQueryParam();
        queryParams.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        queryParams.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));
        queryParams.setStatus("PAYMENT_SUCCESSFUL");

        Long customerId = 1L;

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findByCustomerIdAndDateBetweenAndStatus(customerId, queryParams.getStartDate(), queryParams.getEndDate(), OrderStatus.PAYMENT_SUCCESSFUL))
                .thenReturn(List.of(order));

        List<OrderGetDto> orders = orderService.getOrdersByCustomerId(queryParams, customerId);

        assertEquals(1, orders.size());
        verify(repository).findByCustomerIdAndDateBetweenAndStatus(customerId, queryParams.getStartDate(), queryParams.getEndDate(), OrderStatus.PAYMENT_SUCCESSFUL);
    }

    @Test
    void testGetOrdersByCustomerIdWithDefaultDates() {
        GetOrderByCustomerIdQueryParam queryParams = new GetOrderByCustomerIdQueryParam();
        queryParams.setStartDate(null);
        queryParams.setEndDate(null);

        Long customerId = 1L;

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PAYMENT_SUCCESSFUL);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findByCustomerId(customerId)).thenReturn(List.of(order));

        List<OrderGetDto> orders = orderService.getOrdersByCustomerId(queryParams, customerId);

        assertEquals(1, orders.size());
        verify(repository).findByCustomerId(customerId);
    }

    @Test
    void testUpdateOrderStatusSuccessfulPayment() {
        OrderPaymentStatusMessage message = new OrderPaymentStatusMessage();
        message.setOrderId("67322e3e636c1b1649ba3b27");
        message.setStatus("PAYMENT_SUCCESSFUL");

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findById(message.getOrderId())).thenReturn(Optional.of(order));

        orderService.updateOrderStatus(message);

        verify(repository).save(order);
        verify(catalogSkusProducerService).sendSkusMessage(any());
    }

    @Test
    void testUpdateOrderStatusPaymentAmountNotAvailable() {
        OrderPaymentStatusMessage message = new OrderPaymentStatusMessage();
        message.setOrderId("67322e3e636c1b1649ba3b27");
        message.setStatus("PAYMENT_AMOUNT_NOT_AVAILABLE");

        Order order = new Order();
        order.setId("67322e3e636c1b1649ba3b27");
        order.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order.setDate(LocalDateTime.now());
        order.setCustomer(new Customer(1L, 1L));
        order.setPayment(new Payment(1L, 4));
        order.setCart(List.of(new CartItem(1L, 2)));

        when(repository.findById(message.getOrderId())).thenReturn(Optional.of(order));

        orderService.updateOrderStatus(message);

        verify(repository).save(order);
        verify(catalogSkusProducerService, never()).sendSkusMessage(any());
    }

    @Test
    void testUpdateOrderStatusOrderNotFound() {
        OrderPaymentStatusMessage message = new OrderPaymentStatusMessage();
        message.setOrderId("67322e3e636c1b1649ba3b27");
        message.setStatus("PAYMENT_SUCCESSFUL");

        when(repository.findById(message.getOrderId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            orderService.updateOrderStatus(message);
        });
    }
}

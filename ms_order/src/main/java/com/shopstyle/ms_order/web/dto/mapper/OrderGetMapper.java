package com.shopstyle.ms_order.web.dto.mapper;

import com.shopstyle.ms_order.entity.CartItem;
import com.shopstyle.ms_order.entity.Customer;
import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.Payment;
import com.shopstyle.ms_order.web.dto.CartItemDto;
import com.shopstyle.ms_order.web.dto.CustomerDto;
import com.shopstyle.ms_order.web.dto.OrderGetDto;
import com.shopstyle.ms_order.web.dto.PaymentDto;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class OrderGetMapper {

    public static OrderGetDto toOrderGetDto(Order order) {
        OrderGetDto orderGetDto = new OrderGetDto();
        orderGetDto.setCustomer(customerToCustomerDto(order.getCustomer()));
        orderGetDto.setPayment(paymentToPaymentDto(order.getPayment()));
        orderGetDto.setCart(order.getCart().stream().map(OrderGetMapper::cartItemToCartItemDto).collect(Collectors.toList()));

        return orderGetDto;
    }

    private static CustomerDto customerToCustomerDto(Customer customer) {
        return new ModelMapper().map(customer, CustomerDto.class);
    }

    private static PaymentDto paymentToPaymentDto(Payment payment) {
        return new ModelMapper().map(payment, PaymentDto.class);
    }

    private static CartItemDto cartItemToCartItemDto(CartItem cartItem) {
        return new ModelMapper().map(cartItem, CartItemDto.class);
    }
}

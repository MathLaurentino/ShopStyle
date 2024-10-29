package com.shopstyle.ms_order.web.dto.mapper;

import com.shopstyle.ms_order.entity.CartItem;
import com.shopstyle.ms_order.entity.Customer;
import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.Payment;
import com.shopstyle.ms_order.web.dto.*;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;

public class OrderMapper {

    public static Order toOrder(OrderReqDto dto) {
        Order order = new Order();
        order.setCustomer(customerDtoToCustomer(dto.getCustomer()));
        order.setPayment(paymentDtoToPayment(dto.getPayment()));
        order.setCart(dto.getCart().stream().map(OrderMapper::cartItemDtoToCartItem).collect(Collectors.toList()));

        return order;
    }

    public static OrderGetDto toDto(Order order) {
        return new ModelMapper().map(order, OrderGetDto.class);
    }

    private static Customer customerDtoToCustomer(CustomerDto dto) {
        return new ModelMapper().map(dto, Customer.class);
    }

    private static Payment paymentDtoToPayment(PaymentDto dto) {
        return new ModelMapper().map(dto, Payment.class);
    }

    private static CartItem cartItemDtoToCartItem(CartItemDto dto) {
        return new ModelMapper().map(dto, CartItem.class);
    }

}

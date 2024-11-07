package com.shopstyle.ms_order.web.dto;

import com.shopstyle.ms_order.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderGetDto {

    private String id;
    private CustomerDto customer;
    private PaymentDto payment;
    private List<CartItemDto> cart;
    private LocalDateTime date;
    private OrderStatus status;
    private BigDecimal total;

}

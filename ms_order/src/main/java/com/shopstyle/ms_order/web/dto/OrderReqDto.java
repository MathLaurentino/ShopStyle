package com.shopstyle.ms_order.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {

    @NotNull
    private CustomerDto customer;

    @NotNull
    private PaymentDto payment;

    @NotNull
    private List<CartItemDto> cart;

}
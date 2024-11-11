package com.shopstyle.bff_shop.web.dto.ms_order;

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

    private CustomerDto customer;
    private PaymentDto payment;
    private List<CartItemDto> cart;

}
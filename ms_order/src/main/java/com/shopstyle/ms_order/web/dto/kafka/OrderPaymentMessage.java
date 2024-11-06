package com.shopstyle.ms_order.web.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentMessage implements Serializable {
    private String orderId;
    private PaymentDto payment;
}
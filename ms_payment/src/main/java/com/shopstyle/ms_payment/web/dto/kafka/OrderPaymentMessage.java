package com.shopstyle.ms_payment.web.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaymentMessage {
    private String orderId;
    private PaymentDto payment;
}
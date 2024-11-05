package com.shopstyle.ms_order.web.dto.kafka;

import com.shopstyle.ms_order.entity.Payment;
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
    private Payment payment;
}
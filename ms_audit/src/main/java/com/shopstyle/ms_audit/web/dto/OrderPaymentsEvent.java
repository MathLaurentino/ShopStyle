package com.shopstyle.ms_audit.web.dto;

import com.shopstyle.ms_audit.entity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderPaymentsEvent {

    private String orderId;
    private PaymentInfo payment;

}
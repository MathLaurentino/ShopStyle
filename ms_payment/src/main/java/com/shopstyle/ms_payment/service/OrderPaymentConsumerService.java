package com.shopstyle.ms_payment.service;

import com.shopstyle.ms_payment.web.dto.kafka.OrderPaymentMessage;

public interface OrderPaymentConsumerService {

    void consumeOrderPaymentMessage(OrderPaymentMessage message);

}

package com.shopstyle.ms_order.kafka;

import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;


public interface OrderPaymentProducerService {

    void sendOrderPaymentMessage(OrderPaymentMessage message);

}

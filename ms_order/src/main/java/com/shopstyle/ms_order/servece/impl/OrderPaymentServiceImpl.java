package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.servece.OrderPaymentService;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderPaymentServiceImpl implements OrderPaymentService {

    private final KafkaTemplate<String, OrderPaymentMessage> kafkaTemplate;

    public void sendOrderPaymentMessage(OrderPaymentMessage message) {
        kafkaTemplate.send("order-payments", message);
    }
}

package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.servece.OrderPaymentProducerService;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderPaymentProducerServiceImpl implements OrderPaymentProducerService {

    private final KafkaTemplate<String, OrderPaymentMessage> kafkaTemplate;

    @Override
    public void sendOrderPaymentMessage(OrderPaymentMessage message) {
        log.info("mensagem ao order-payments, orderId: " + message.getOrderId());
        kafkaTemplate.send("order-payments", message);
    }
}

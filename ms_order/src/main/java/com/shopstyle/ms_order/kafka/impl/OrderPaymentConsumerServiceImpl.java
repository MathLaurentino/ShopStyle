package com.shopstyle.ms_order.kafka.impl;

import com.shopstyle.ms_order.kafka.OrderPaymentConsumerService;
import com.shopstyle.ms_order.servece.OrderService;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderPaymentConsumerServiceImpl implements OrderPaymentConsumerService {

    private final OrderService orderService;

    @Override
    @KafkaListener(topics = "payment-status", groupId = "payment-group", containerFactory = "jsonContainerFactory")
    public void consumeOrderPaymentStatusMessage(OrderPaymentStatusMessage message) {
        log.info("ms-order consume message: " + message);
        orderService.updateOrderStatus(message);
    }

}

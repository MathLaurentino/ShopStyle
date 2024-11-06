package com.shopstyle.ms_payment.service.impl;

import com.shopstyle.ms_payment.service.OrderPaymentProducerService;
import com.shopstyle.ms_payment.web.dto.kafka.PaymentResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderPaymentProducerServiceImpl implements OrderPaymentProducerService {

    private final KafkaTemplate<String, PaymentResponseMessage> kafkaTemplate;

    @Override
    public void sendPaymentStatus(PaymentResponseMessage response) {
        kafkaTemplate.send("payment-status", response);
    }

}

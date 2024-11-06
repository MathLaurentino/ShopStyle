package com.shopstyle.ms_payment.service.impl;

import com.shopstyle.ms_payment.entity.Payment;
import com.shopstyle.ms_payment.repository.PaymentRepository;
import com.shopstyle.ms_payment.service.OrderPaymentConsumerService;
import com.shopstyle.ms_payment.web.dto.kafka.OrderPaymentMessage;
import com.shopstyle.ms_payment.web.dto.kafka.PaymentDto;
import com.shopstyle.ms_payment.web.dto.kafka.PaymentResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderPaymentConsumerServiceImpl implements OrderPaymentConsumerService {

    private final OrderPaymentProducerServiceImpl orderPaymentPublisherService;
    private final PaymentRepository paymentRepository;

    @KafkaListener(topics = "order-payments", groupId = "payment-group", containerFactory = "jsonContainerFactory")
    public void consumeOrderPaymentMessage(OrderPaymentMessage message) {

        String status = validatePayment(message.getPayment());

        PaymentResponseMessage response = new PaymentResponseMessage();
        response.setOrderId(message.getOrderId());
        response.setStatus(status);

        orderPaymentPublisherService.sendPaymentStatus(response);
    }

    private String validatePayment(PaymentDto paymentDto) {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentDto.getId());

        if (paymentOptional.isEmpty()) {
            return "PAYMENT_NOT_FOUND";
        }
        Payment payment = paymentOptional.get();
        if (!payment.getActive()) {
            return "PAYMENT_INACTIVE";
        }
        if (!payment.getInstallments()) {
            return "PAYMENT_NOT_INSTALLMENT";
        }
        if (payment.getInstallment().getAmount() < paymentDto.getInstallments()) {
            return "PAYMENT_AMOUNT_NOT_AVAILABLE";
        }

        return "PAYMENT_SUCCESSFUL";
    }
}

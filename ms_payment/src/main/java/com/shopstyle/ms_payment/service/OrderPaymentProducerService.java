package com.shopstyle.ms_payment.service;

import com.shopstyle.ms_payment.web.dto.kafka.PaymentResponseMessage;

public interface OrderPaymentProducerService {

    void sendPaymentStatus(PaymentResponseMessage response);

}

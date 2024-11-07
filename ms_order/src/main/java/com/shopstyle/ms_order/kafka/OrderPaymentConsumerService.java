package com.shopstyle.ms_order.kafka;

import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;

public interface OrderPaymentConsumerService {

    void consumeOrderPaymentStatusMessage(OrderPaymentStatusMessage message);

}

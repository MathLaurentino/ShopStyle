package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;

public interface OrderPaymentConsumerService {

    void consumeOrderPaymentStatusMessage(OrderPaymentStatusMessage message);

}

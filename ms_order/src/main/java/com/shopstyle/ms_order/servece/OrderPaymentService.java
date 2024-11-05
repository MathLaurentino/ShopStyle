package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;


public interface OrderPaymentService {

    void sendOrderPaymentMessage(OrderPaymentMessage message);

}

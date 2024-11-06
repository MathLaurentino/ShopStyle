package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.OrderGetDto;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;

public interface OrderService {

    OrderGetDto createOrder(OrderReqDto dto);

    void updateOrderStatus(OrderPaymentStatusMessage message);

}

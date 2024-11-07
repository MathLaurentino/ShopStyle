package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.*;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;

import java.util.List;

public interface OrderService {

    CreatedOrderDto createOrder(OrderReqDto dto);

    List<OrderGetDto> getOrders(GetOrderQueryParam queryParams);

    List<OrderGetDto> getOrdersByCustomerId(GetOrderByCustomerIdQueryParam queryParam, Long customerId);

    void updateOrderStatus(OrderPaymentStatusMessage message);

}

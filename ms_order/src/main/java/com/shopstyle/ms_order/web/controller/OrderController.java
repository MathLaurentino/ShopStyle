package com.shopstyle.ms_order.web.controller;

import com.shopstyle.ms_order.web.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderController {

    ResponseEntity<CreatedOrderDto> createOrder(OrderReqDto dto);

    ResponseEntity<List<OrderGetDto>> getOrders(GetOrderQueryParam queryParams);

    ResponseEntity<List<OrderGetDto>> getOrdersByCustomerId(GetOrderByCustomerIdQueryParam queryParam, Long customerId);

}

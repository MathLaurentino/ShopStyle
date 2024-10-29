package com.shopstyle.ms_order.web.controller;

import com.shopstyle.ms_order.web.dto.OrderGetDto;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import org.springframework.http.ResponseEntity;

public interface OrderController {

    ResponseEntity<OrderGetDto> createOrder(OrderReqDto dto);

}

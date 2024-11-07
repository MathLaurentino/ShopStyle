package com.shopstyle.ms_order.web.controller.impl;

import com.shopstyle.ms_order.servece.OrderService;
import com.shopstyle.ms_order.web.controller.OrderController;
import com.shopstyle.ms_order.web.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/orders")
@RestController
public class OrderControllerImpl implements OrderController {

    private final OrderService service;

    @Override
    @PostMapping
    public ResponseEntity<CreatedOrderDto> createOrder(@Valid @RequestBody OrderReqDto dto) {
        var getDto = service.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(getDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<OrderGetDto>> getOrders(
            @Valid @ModelAttribute GetOrderQueryParam queryParams)
    {
        List<OrderGetDto> orderGetDto = service.getOrders(queryParams);
        return ResponseEntity.ok(orderGetDto);
    }

    @Override
    public ResponseEntity<List<OrderGetDto>> getOrdersByCustomerId(GetOrderByCustomerIdQueryParam queryParam, Long customerId) {
        return null;
    }

}

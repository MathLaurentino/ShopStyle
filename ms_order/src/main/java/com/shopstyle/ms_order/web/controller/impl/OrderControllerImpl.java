package com.shopstyle.ms_order.web.controller.impl;

import com.shopstyle.ms_order.servece.OrderService;
import com.shopstyle.ms_order.web.controller.OrderController;
import com.shopstyle.ms_order.web.dto.OrderGetDto;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/orders")
@RestController
public class OrderControllerImpl implements OrderController {

    private final OrderService service;

    @Override
    @PostMapping
    public ResponseEntity<OrderGetDto> createOrder(@Valid @RequestBody OrderReqDto dto) {
        var getDto = service.createOrder(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(getDto);
    }

}

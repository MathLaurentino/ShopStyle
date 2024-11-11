package com.shopstyle.bff_shop.web.controller;

import com.shopstyle.bff_shop.service.OrderService;
import com.shopstyle.bff_shop.web.dto.ms_order.CreatedOrderDto;
import com.shopstyle.bff_shop.web.dto.ms_order.OrderGetDto;
import com.shopstyle.bff_shop.web.dto.ms_order.OrderReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<CreatedOrderDto> createOrder(@RequestBody OrderReqDto dto){
        return service.createOrder(dto);
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<List<OrderGetDto>> getOrdersByCustomerId(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String status,
            @PathVariable Long customerId) {
        return service.getOrdersByCustomerId(
                startDate,
                endDate,
                status,
                customerId);
    }

}

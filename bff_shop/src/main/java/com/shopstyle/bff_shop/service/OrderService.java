package com.shopstyle.bff_shop.service;

import com.shopstyle.bff_shop.web.dto.ms_order.CreatedOrderDto;
import com.shopstyle.bff_shop.web.dto.ms_order.OrderGetDto;
import com.shopstyle.bff_shop.web.dto.ms_order.OrderReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ms-order", path = "/v1/orders")
public interface OrderService {

    @PostMapping
    public ResponseEntity<CreatedOrderDto> createOrder(@RequestBody OrderReqDto dto);

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderGetDto>> getOrdersByCustomerId(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String status,
            @PathVariable Long customerId);
}

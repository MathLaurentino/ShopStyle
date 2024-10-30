package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.enums.OrderStatus;
import com.shopstyle.ms_order.repository.OrderRepository;
import com.shopstyle.ms_order.servece.CustomerService;
import com.shopstyle.ms_order.servece.OrderService;
import com.shopstyle.ms_order.servece.SkuService;
import com.shopstyle.ms_order.web.dto.OrderGetDto;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import com.shopstyle.ms_order.web.dto.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final SkuService skuService;

    @Override
    public OrderGetDto createOrder(OrderReqDto dto) {
        Order order = OrderMapper.toOrder(dto);

        customerService.customerDataIsValid(dto);
        var total = skuService.cartTotalPrice(dto.getCart());

        order.setDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order.setTotal(total);

        return OrderMapper.toDto(repository.save(order));
    }

}

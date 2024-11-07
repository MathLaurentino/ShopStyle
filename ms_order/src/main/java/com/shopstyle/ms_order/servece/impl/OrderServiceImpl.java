package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.enums.OrderStatus;
import com.shopstyle.ms_order.exception.EntityNotFoundException;
import com.shopstyle.ms_order.kafka.CatalogSkusProducerService;
import com.shopstyle.ms_order.kafka.OrderPaymentProducerService;
import com.shopstyle.ms_order.repository.OrderRepository;
import com.shopstyle.ms_order.servece.*;
import com.shopstyle.ms_order.web.dto.OrderGetDto;
import com.shopstyle.ms_order.web.dto.OrderReqDto;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentMessage;
import com.shopstyle.ms_order.web.dto.kafka.OrderPaymentStatusMessage;
import com.shopstyle.ms_order.web.dto.kafka.PaymentDto;
import com.shopstyle.ms_order.web.dto.kafka.SkusMessage;
import com.shopstyle.ms_order.web.dto.mapper.OrderMapper;
import com.shopstyle.ms_order.web.dto.kafka.SkuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CustomerService customerService;
    private final SkuService skuService;
    private final OrderPaymentProducerService orderPaymentService;
    private final CatalogSkusProducerService catalogSkusProducerService;

    @Override
    public OrderGetDto createOrder(OrderReqDto dto) {
        Order order = OrderMapper.toOrder(dto);

        customerService.customerDataIsValid(dto);
        var total = skuService.cartTotalPrice(dto.getCart());

        order.setDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PROCESSING_PAYMENT);
        order.setTotal(total);

        Order createdOrder = repository.save(order);

        OrderPaymentMessage message = new OrderPaymentMessage();
        message.setOrderId(createdOrder.getId());
        message.setPayment(new PaymentDto(dto.getPayment().getId(), dto.getPayment().getInstallments()));
        orderPaymentService.sendOrderPaymentMessage(message);

        return OrderMapper.toDto(createdOrder);
    }

    @Override
    public void updateOrderStatus(OrderPaymentStatusMessage message) {
        Order order = repository.findById(message.getOrderId()).orElseThrow(() ->
                new EntityNotFoundException("Order not found"));

        order.setStatus(OrderStatus.valueOf(message.getStatus()));
        repository.save(order);

        if (order.getStatus() == OrderStatus.PAYMENT_SUCCESSFUL) {
            SkusMessage skusMessage = SkuMapper.toSkusMessage(order);
            catalogSkusProducerService.sendSkusMessage(skusMessage);
        }
    }

}

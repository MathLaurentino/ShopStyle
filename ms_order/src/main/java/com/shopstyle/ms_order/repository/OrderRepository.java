package com.shopstyle.ms_order.repository;

import com.shopstyle.ms_order.entity.Order;
import com.shopstyle.ms_order.entity.enums.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByDateBetweenAndStatus(LocalDateTime startDate, LocalDateTime endDate, OrderStatus status);

    List<Order> findByCustomerId(Long customerId);

    List<Order> findByCustomerIdAndStatus(Long customerId, OrderStatus status);

    List<Order> findByCustomerIdAndDateBetween(Long customerId, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByCustomerIdAndDateBetweenAndStatus(Long customerId, LocalDateTime startDate, LocalDateTime endDate, OrderStatus status);

}

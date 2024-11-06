package com.shopstyle.ms_order.repository;

import com.shopstyle.ms_order.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}

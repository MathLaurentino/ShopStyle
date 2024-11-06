package com.shopstyle.ms_order.entity;

import com.shopstyle.ms_order.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    @Field("customer")
    private Customer customer;

    @Field("payment")
    private Payment payment;

    @Field("cart")
    private List<CartItem> cart;

    @Field("date")
    private LocalDateTime date;

    @Field("status")
    private OrderStatus status;

    @Field("total")
    private BigDecimal total;

}

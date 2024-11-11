package com.shopstyle.bff_shop.web.dto.ms_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedOrderDto {

    private String id;
    private LocalDateTime date;
    private OrderStatus status;
    private BigDecimal total;

}
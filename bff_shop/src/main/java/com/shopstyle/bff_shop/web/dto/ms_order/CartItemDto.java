package com.shopstyle.bff_shop.web.dto.ms_order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CartItemDto {

    private Long skuId;
    private Integer quantity;

}
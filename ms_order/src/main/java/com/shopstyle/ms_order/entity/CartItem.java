package com.shopstyle.ms_order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Field("skuId")
    private Long skuId;

    @Field("quantity")
    private int quantity;

}

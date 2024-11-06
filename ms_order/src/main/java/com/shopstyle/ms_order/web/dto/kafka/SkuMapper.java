package com.shopstyle.ms_order.web.dto.kafka;

import com.shopstyle.ms_order.entity.CartItem;
import com.shopstyle.ms_order.entity.Order;

import java.util.stream.Collectors;

public class SkuMapper {

    public static SkusMessage toSkusMessage(Order order) {
        SkusMessage skusMessage = new SkusMessage();
        skusMessage.setOrderId(order.getId());
        skusMessage.setSkus(order.getCart().stream().map(SkuMapper::toSkuDto).collect(Collectors.toList()));

        return skusMessage;
    }

    private static SkuDto toSkuDto(CartItem cartItem) {
        SkuDto skuDto = new SkuDto();
        skuDto.setId(cartItem.getSkuId());
        skuDto.setQuantity(cartItem.getQuantity());

        return skuDto;
    }
}

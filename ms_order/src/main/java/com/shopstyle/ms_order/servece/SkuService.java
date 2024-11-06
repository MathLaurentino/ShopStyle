package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

public interface SkuService {

    BigDecimal cartTotalPrice(List<CartItemDto> dtos);

}

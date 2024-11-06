package com.shopstyle.ms_order.web.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkuDtoFeign {

    private Long id;
    private Integer quantity;
    private BigDecimal price;

}

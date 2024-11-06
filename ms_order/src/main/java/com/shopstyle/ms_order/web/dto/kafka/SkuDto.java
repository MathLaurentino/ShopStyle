package com.shopstyle.ms_order.web.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkuDto implements Serializable {

    private Long id;
    private Integer quantity;

}

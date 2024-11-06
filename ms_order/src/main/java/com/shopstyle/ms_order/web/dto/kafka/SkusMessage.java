package com.shopstyle.ms_order.web.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SkusMessage implements Serializable {

    private String orderId;
    private List<SkuDto> skus;
}

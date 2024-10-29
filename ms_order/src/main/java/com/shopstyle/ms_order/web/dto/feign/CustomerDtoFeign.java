package com.shopstyle.ms_order.web.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDtoFeign {

    private Long id;
    private Boolean active;
    private Set<AddressDtoFeign> addresses;

}

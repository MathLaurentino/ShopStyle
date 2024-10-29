package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.OrderReqDto;

public interface CustomerService {

    Boolean customerDataIsValid(OrderReqDto dto);

}

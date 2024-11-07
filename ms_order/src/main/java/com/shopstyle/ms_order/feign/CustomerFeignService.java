package com.shopstyle.ms_order.feign;

import com.shopstyle.ms_order.web.dto.feign.CustomerDtoFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer", path = "/v1/customers")
public interface CustomerFeignService {

    @GetMapping("/{id}")
    ResponseEntity<CustomerDtoFeign> findCustomerById(@PathVariable("id") Long id);

}

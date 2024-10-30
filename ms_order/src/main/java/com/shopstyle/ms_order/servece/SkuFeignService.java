package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.feign.SkuDtoFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-catalog", path = "/v1/skus")
public interface SkuFeignService {

    @GetMapping("/{id}")
    ResponseEntity<SkuDtoFeign> findSkuById(@PathVariable("id") Long id);

}

package com.shopstyle.bff_shop.service;

import com.shopstyle.bff_shop.web.dto.ms_payment.PaymentGetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "ms-payment", path = "/v1")
public interface PaymentService {

    @GetMapping("/payments")
    ResponseEntity<List<PaymentGetDto>> getPayment();

}

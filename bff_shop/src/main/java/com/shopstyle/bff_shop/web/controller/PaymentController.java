package com.shopstyle.bff_shop.web.controller;

import com.shopstyle.bff_shop.service.PaymentService;
import com.shopstyle.bff_shop.web.dto.ms_payment.PaymentGetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class PaymentController {

    private final PaymentService service;

    @GetMapping("/payments")
    ResponseEntity<List<PaymentGetDto>> getPayment() {
        return service.getPayment();
    }

}

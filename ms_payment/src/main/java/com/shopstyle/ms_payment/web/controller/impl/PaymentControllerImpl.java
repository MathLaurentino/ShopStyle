package com.shopstyle.ms_payment.web.controller.impl;

import com.shopstyle.ms_payment.service.PaymentService;
import com.shopstyle.ms_payment.web.controller.PaymentController;
import com.shopstyle.ms_payment.web.dto.PaymentGetDto;
import com.shopstyle.ms_payment.web.dto.PaymentReqDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("v1/payments")
@RestController
public class PaymentControllerImpl implements PaymentController {

    private final PaymentService service;

    @Override
    @PostMapping
    public ResponseEntity<PaymentGetDto> createPayment(@Valid @RequestBody PaymentReqDto dto) {
        var getDto = service.createPayment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(getDto);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PaymentGetDto> updatePayment(@Valid @RequestBody PaymentReqDto dto, @PathVariable Long id) {
        var getDto = service.updatePayment(dto, id);
        return ResponseEntity.ok(getDto);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PaymentGetDto>> getPayment() {
        var payments = service.getPayment();
        return ResponseEntity.ok(payments);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

}

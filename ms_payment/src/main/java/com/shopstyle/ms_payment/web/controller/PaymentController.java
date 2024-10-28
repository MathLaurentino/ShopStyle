package com.shopstyle.ms_payment.web.controller;

import com.shopstyle.ms_payment.web.dto.PaymentGetDto;
import com.shopstyle.ms_payment.web.dto.PaymentReqDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentController {

    ResponseEntity<PaymentGetDto> createPayment(PaymentReqDto dto);

    ResponseEntity<PaymentGetDto> updatePayment(PaymentReqDto dto, Long id);

    ResponseEntity<List<PaymentGetDto>> getPayment();

    ResponseEntity<Void> deletePayment(Long id);

}

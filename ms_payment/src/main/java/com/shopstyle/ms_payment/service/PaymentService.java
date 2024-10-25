package com.shopstyle.ms_payment.service;

import com.shopstyle.ms_payment.web.dto.PaymentGetDto;
import com.shopstyle.ms_payment.web.dto.PaymentReqDto;

import java.util.List;

public interface PaymentService {

    PaymentGetDto createPayment(PaymentReqDto dto);

    PaymentGetDto updatePayment(PaymentReqDto dto, Long id);

    List<PaymentGetDto> getPayment();

    void deletePayment(Long id);

}

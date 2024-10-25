package com.shopstyle.ms_payment.web.dto.mapper;

import com.shopstyle.ms_payment.entity.Payment;
import com.shopstyle.ms_payment.web.dto.PaymentGetDto;
import com.shopstyle.ms_payment.web.dto.PaymentReqDto;
import org.modelmapper.ModelMapper;

public class PaymentMapper {

    public static Payment toPayment(PaymentReqDto dto) {
        return new ModelMapper().map(dto, Payment.class);
    }

    public static PaymentGetDto toDto(Payment payment) {
        return new ModelMapper().map(payment, PaymentGetDto.class);
    }
}

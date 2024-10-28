package com.shopstyle.ms_payment.service.impl;

import com.shopstyle.ms_payment.entity.Payment;
import com.shopstyle.ms_payment.exception.EntityNotFoundException;
import com.shopstyle.ms_payment.repository.PaymentRepository;
import com.shopstyle.ms_payment.service.PaymentService;
import com.shopstyle.ms_payment.web.dto.PaymentGetDto;
import com.shopstyle.ms_payment.web.dto.PaymentReqDto;
import com.shopstyle.ms_payment.web.dto.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public PaymentGetDto createPayment(PaymentReqDto dto) {
        Payment payment = PaymentMapper.toPayment(dto);

        return PaymentMapper.toDto(repository.save(payment));
    }

    @Override
    public PaymentGetDto updatePayment(PaymentReqDto dto, Long id) {
        Payment payment = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Payment not found"));

        payment.setType(dto.getType());
        payment.setInstallments(dto.getInstallments().equals("true"));
        payment.setActive(dto.getActive().equals("true"));

        return PaymentMapper.toDto(repository.save(payment));
    }

    @Override
    public List<PaymentGetDto> getPayment() {
        List<Payment> payments = repository.findAll();

        return payments.stream().map(PaymentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Payment not found"));

        repository.deleteById(id);
    }
}

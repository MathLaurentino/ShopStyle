package com.shopstyle.ms_payment.service.impl;

import com.shopstyle.ms_payment.entity.Installment;
import com.shopstyle.ms_payment.entity.Payment;
import com.shopstyle.ms_payment.exception.EntityNotFoundException;
import com.shopstyle.ms_payment.exception.InstallmentNotAcceptedException;
import com.shopstyle.ms_payment.exception.PaymentAlreadyHaveAnInstallmentException;
import com.shopstyle.ms_payment.repository.InstallmentRepository;
import com.shopstyle.ms_payment.repository.PaymentRepository;
import com.shopstyle.ms_payment.service.InstallmentService;
import com.shopstyle.ms_payment.web.dto.InstallmentGetDto;
import com.shopstyle.ms_payment.web.dto.InstallmentReqDto;
import com.shopstyle.ms_payment.web.dto.mapper.InstallmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class InstallmentServiceImpl implements InstallmentService {

    private final InstallmentRepository installmentRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public InstallmentGetDto createInstallment(InstallmentReqDto dto) {
        Payment payment = paymentRepository.findById(dto.getPaymentId()).orElseThrow(() ->
                new EntityNotFoundException("Payment not found"));

        if (!payment.getInstallments()) {
            throw new InstallmentNotAcceptedException("Installments not accepted for this payment method");
        }

        if (payment.getInstallment() != null) {
            throw  new PaymentAlreadyHaveAnInstallmentException("Payment already have an installment");
        }

        Installment installment = InstallmentMapper.toInstallment(dto);
        installment.setPayment(payment);

        return InstallmentMapper.toDto(installmentRepository.save(installment));
    }

    @Override
    public InstallmentGetDto updateInstallment(InstallmentReqDto dto, Long id) {
        Installment installment = installmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Installment not found"));

        Payment payment = paymentRepository.findById(dto.getPaymentId()).orElseThrow(() ->
                new EntityNotFoundException("Payment not found"));

        if (!payment.getInstallments()) {
            throw new InstallmentNotAcceptedException("Installments not accepted for this payment method");
        }

        if (payment.getInstallment() != null && !Objects.equals(payment.getInstallment().getId(), id)) {
            throw  new PaymentAlreadyHaveAnInstallmentException("Payment already have an installment");
        }

        installment.setAmount(dto.getAmount());
        installment.setBrand(dto.getBrand());
        installment.setPayment(payment);

        return InstallmentMapper.toDto(installmentRepository.save(installment));
    }

    @Override
    public void deleteInstallment(Long id) {
        installmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Installment not found"));

        installmentRepository.deleteById(id);
    }

}

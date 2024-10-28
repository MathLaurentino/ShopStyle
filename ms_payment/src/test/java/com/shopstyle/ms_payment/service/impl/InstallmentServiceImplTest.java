package com.shopstyle.ms_payment.service.impl;

import com.shopstyle.ms_payment.entity.Installment;
import com.shopstyle.ms_payment.exception.EntityNotFoundException;
import com.shopstyle.ms_payment.exception.InstallmentNotAcceptedException;
import com.shopstyle.ms_payment.exception.PaymentAlreadyHaveAnInstallmentException;
import com.shopstyle.ms_payment.repository.InstallmentRepository;
import com.shopstyle.ms_payment.repository.PaymentRepository;
import com.shopstyle.ms_payment.web.dto.InstallmentGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.shopstyle.ms_payment.builder.InstallmentBuilder.anInstallment;
import static com.shopstyle.ms_payment.builder.InstallmentReqDtoBuilder.anInstallmentReqDto;
import static com.shopstyle.ms_payment.builder.PaymentBuilder.aPayment;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InstallmentServiceImplTest {

    @Mock
    private InstallmentRepository installmentRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private InstallmentServiceImpl installmentService;

    @Test
    void createInstallment_Success() {
        var dto = anInstallmentReqDto().withAmount(3).withPaymentId(1L).get();
        var payment = aPayment().withId(dto.getPaymentId()).withInstallments(true).get();
        var savedInstallment = anInstallment().withAmount(dto.getAmount()).withPayment(payment).get();

        when(paymentRepository.findById(dto.getPaymentId())).thenReturn(Optional.of(payment));
        when(installmentRepository.save(any(Installment.class))).thenReturn(savedInstallment);

        InstallmentGetDto result = installmentService.createInstallment(dto);

        assertNotNull(result);
        assertEquals(dto.getAmount(), result.getAmount());
        assertEquals(dto.getPaymentId(), result.getPaymentId());
        verify(installmentRepository).save(any(Installment.class));
    }

    @Test
    void createInstallment_PaymentNotFound_ThrowsEntityNotFoundException() {
        var dto = anInstallmentReqDto().withPaymentId(1L).get();
        when(paymentRepository.findById(dto.getPaymentId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> installmentService.createInstallment(dto));
    }

    @Test
    void createInstallment_InstallmentNotAccepted_ThrowsInstallmentNotAcceptedException() {
        var dto = anInstallmentReqDto().withPaymentId(1L).get();
        var payment = aPayment().withId(dto.getPaymentId()).withInstallments(false).get();

        when(paymentRepository.findById(dto.getPaymentId())).thenReturn(Optional.of(payment));

        assertThrows(InstallmentNotAcceptedException.class, () -> installmentService.createInstallment(dto));
    }

    @Test
    void createInstallment_PaymentAlreadyHasInstallment_ThrowsException() {
        var dto = anInstallmentReqDto().withPaymentId(1L).get();
        var payment = aPayment().withId(dto.getPaymentId()).withInstallment(anInstallment().get()).get();

        when(paymentRepository.findById(dto.getPaymentId())).thenReturn(Optional.of(payment));

        assertThrows(PaymentAlreadyHaveAnInstallmentException.class, () -> installmentService.createInstallment(dto));
    }

    @Test
    void updateInstallment_Success() {
        Long installmentId = 1L;
        var dto = anInstallmentReqDto().withAmount(5).withPaymentId(2L).get();
        var existingInstallment = anInstallment().withId(installmentId).get();
        var payment = aPayment().withId(dto.getPaymentId()).withInstallments(true).get();

        when(installmentRepository.findById(installmentId)).thenReturn(Optional.of(existingInstallment));
        when(paymentRepository.findById(dto.getPaymentId())).thenReturn(Optional.of(payment));
        when(installmentRepository.save(existingInstallment)).thenReturn(existingInstallment);

        InstallmentGetDto result = installmentService.updateInstallment(dto, installmentId);

        assertNotNull(result);
        assertEquals(dto.getAmount(), result.getAmount());
        assertEquals(dto.getPaymentId(), result.getPaymentId());
        verify(installmentRepository).save(existingInstallment);
    }

    @Test
    void updateInstallment_InstallmentNotFound_ThrowsEntityNotFoundException() {
        Long installmentId = 1L;
        var dto = anInstallmentReqDto().get();

        when(installmentRepository.findById(installmentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> installmentService.updateInstallment(dto, installmentId));
    }

    @Test
    void deleteInstallment_Success() {
        Long installmentId = 1L;
        var existingInstallment = anInstallment().withId(installmentId).get();

        when(installmentRepository.findById(installmentId)).thenReturn(Optional.of(existingInstallment));

        installmentService.deleteInstallment(installmentId);

        verify(installmentRepository).deleteById(installmentId);
    }

    @Test
    void deleteInstallment_InstallmentNotFound_ThrowsEntityNotFoundException() {
        Long installmentId = 1L;

        when(installmentRepository.findById(installmentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> installmentService.deleteInstallment(installmentId));
        verify(installmentRepository, never()).deleteById(anyLong());
    }
}

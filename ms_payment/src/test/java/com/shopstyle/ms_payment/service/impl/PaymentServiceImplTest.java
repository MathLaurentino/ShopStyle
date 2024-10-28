package com.shopstyle.ms_payment.service.impl;

import com.shopstyle.ms_payment.entity.Payment;
import com.shopstyle.ms_payment.exception.EntityNotFoundException;
import com.shopstyle.ms_payment.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.shopstyle.ms_payment.builder.PaymentBuilder.aPayment;
import static com.shopstyle.ms_payment.builder.PaymentReqDtoBuilder.aPaymentReqDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentServiceImpl service;

    @Test
    void createPayment_Success() {
        var dto = aPaymentReqDto().get();
        var payment = aPayment().withType(dto.getType()).get();
        when(repository.save(any(Payment.class))).thenReturn(payment);

        var result = service.createPayment(dto);

        assertNotNull(result);
        assertEquals(dto.getType(), result.getType());
        assertEquals(dto.getInstallments().equals("true"), result.getInstallments());
        verify(repository).save(any(Payment.class));
    }

    @Test
    void updatePayment_Success() {
        Long paymentId = 1L;
        var dto = aPaymentReqDto().withType("Debito").get();
        var existingPayment = aPayment().withId(paymentId).get();
        when(repository.findById(paymentId)).thenReturn(Optional.of(existingPayment));
        when(repository.save(existingPayment)).thenReturn(existingPayment);

        var result = service.updatePayment(dto, paymentId);

        assertNotNull(result);
        assertEquals(dto.getType(), result.getType());
        verify(repository).save(existingPayment);
    }

    @Test
    void updatePayment_EntityNotFoundException() {
        Long paymentId = 1L;
        var dto = aPaymentReqDto().get();
        when(repository.findById(paymentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updatePayment(dto, paymentId));
        verify(repository, never()).save(any());
    }

    @Test
    void getPayment_Success() {
        var payment1 = aPayment().withId(1L).get();
        var payment2 = aPayment().withId(2L).get();
        when(repository.findAll()).thenReturn(List.of(payment1, payment2));

        var result = service.getPayment();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void deletePayment_Success() {
        Long paymentId = 1L;
        var existingPayment = aPayment().withId(paymentId).get();
        when(repository.findById(paymentId)).thenReturn(Optional.of(existingPayment));

        service.deletePayment(paymentId);

        verify(repository).deleteById(paymentId);
    }

    @Test
    void deletePayment_EntityNotFoundException() {
        Long paymentId = 1L;
        when(repository.findById(paymentId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.deletePayment(paymentId));
        verify(repository, never()).deleteById(anyLong());
    }
}

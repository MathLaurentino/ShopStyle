package com.shopstyle.ms_payment.repository;

import com.shopstyle.ms_payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

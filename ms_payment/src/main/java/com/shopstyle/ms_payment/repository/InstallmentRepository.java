package com.shopstyle.ms_payment.repository;

import com.shopstyle.ms_payment.entity.Installment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentRepository extends JpaRepository<Installment, Long> {
}

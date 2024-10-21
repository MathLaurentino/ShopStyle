package com.shopstyle.ms_customer.repository;

import com.shopstyle.ms_customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

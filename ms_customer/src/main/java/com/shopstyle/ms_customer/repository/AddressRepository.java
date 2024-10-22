package com.shopstyle.ms_customer.repository;

import com.shopstyle.ms_customer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

package com.shopstyle.ms_customer.jwt;

import com.shopstyle.ms_customer.entity.Customer;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

public class JwtUserDetails extends User {

    private Customer customer;

    public JwtUserDetails(Customer customer) {
        super(customer.getEmail(), customer.getPassword(), Collections.emptyList());
        this.customer = customer;
    }

    public Long getId() {
        return this.customer.getId();
    }
}

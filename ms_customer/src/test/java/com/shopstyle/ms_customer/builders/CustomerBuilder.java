package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.entity.Customer;
import com.shopstyle.ms_customer.entity.enums.Sex;

import java.time.LocalDate;

public class CustomerBuilder {

    private Customer customer;

    public CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {
        CustomerBuilder builder = new CustomerBuilder();

        builder.customer = new Customer();
        builder.customer.setCpf("479.932.310-51");
        builder.customer.setFirstName("John");
        builder.customer.setLastName("Marston");
        builder.customer.setSex(Sex.Masculino);
        builder.customer.setBirthdate(LocalDate.now());
        builder.customer.setEmail("customer@customer.com");
        builder.customer.setPassword("Password");
        builder.customer.setActive(true);

        return builder;
    }

    public CustomerBuilder withEmail(String email) {
        customer.setEmail(email);
        return this;
    }

    public CustomerBuilder withCpf(String cpf) {
        customer.setCpf(cpf);
        return this;
    }

    public CustomerBuilder withId(Long id) {
        customer.setId(id);
        return this;
    }

    public Customer get() {
        return this.customer;
    }

}

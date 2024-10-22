package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.web.dto.CustomerPostDto;

import java.time.LocalDate;

public class CustomerPostBuilder {

    private CustomerPostDto customerDto;

    public CustomerPostBuilder() {
    }

    public static CustomerPostBuilder aCustomerPostDto() {
        CustomerPostBuilder builder = new CustomerPostBuilder();

        builder.customerDto = new CustomerPostDto();
        builder.customerDto.setCpf("479.932.310-51");
        builder.customerDto.setFirstName("John");
        builder.customerDto.setLastName("Marston");
        builder.customerDto.setSex("Masculino");
        builder.customerDto.setBirthdate(LocalDate.now());
        builder.customerDto.setEmail("customer@customer.com");
        builder.customerDto.setPassword("Password");
        builder.customerDto.setActive("true");

        return builder;
    }

    public CustomerPostBuilder withEmail(String email) {
        customerDto.setEmail(email);
        return this;
    }

    public CustomerPostBuilder withCpf(String cpf) {
        customerDto.setCpf(cpf);
        return this;
    }

    public CustomerPostDto get() {
        return this.customerDto;
    }

}

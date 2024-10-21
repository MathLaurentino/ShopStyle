package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.entity.enums.Sex;
import com.shopstyle.ms_customer.web.dto.CustomerGetDto;

import java.time.LocalDate;

public class CustomerGetBuilder {

    private CustomerGetDto customerDto;

    public CustomerGetBuilder() {
    }

    public static CustomerGetBuilder aCustomerGetDto() {
        CustomerGetBuilder builder = new CustomerGetBuilder();

        builder.customerDto = new CustomerGetDto();
        builder.customerDto.setCpf("479.932.310-51");
        builder.customerDto.setFirstName("John");
        builder.customerDto.setLastName("Marston");
        builder.customerDto.setSex(Sex.Masculino);
        builder.customerDto.setBirthdate(LocalDate.now());
        builder.customerDto.setEmail("customer@customer.com");
        builder.customerDto.setActive(true);

        return builder;
    }

    public CustomerGetBuilder withEmail(String email) {
        customerDto.setEmail(email);
        return this;
    }

    public CustomerGetBuilder withCpf(String cpf) {
        customerDto.setCpf(cpf);
        return this;
    }

    public CustomerGetDto get() {
        return this.customerDto;
    }

}

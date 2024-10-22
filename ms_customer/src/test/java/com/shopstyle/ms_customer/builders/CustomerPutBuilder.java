package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.web.dto.CustomerPutDto;

import java.time.LocalDate;

public class CustomerPutBuilder {

    private CustomerPutDto customerDto;

    public CustomerPutBuilder() {
    }

    public static CustomerPutBuilder aCustomerPutDto() {
        CustomerPutBuilder builder = new CustomerPutBuilder();

        builder.customerDto = new CustomerPutDto();
        builder.customerDto.setCpf("479.932.310-51");
        builder.customerDto.setFirstName("John");
        builder.customerDto.setLastName("Marston");
        builder.customerDto.setSex("Masculino");
        builder.customerDto.setBirthdate(LocalDate.now());
        builder.customerDto.setEmail("customer@customer.com");
        builder.customerDto.setActive("true");

        return builder;
    }

    public CustomerPutBuilder withEmail(String email) {
        customerDto.setEmail(email);
        return this;
    }

    public CustomerPutBuilder withCpf(String cpf) {
        customerDto.setCpf(cpf);
        return this;
    }

    public CustomerPutBuilder withFirsName(String firsName) {
        customerDto.setFirstName(firsName);
        return this;
    }

    public CustomerPutDto get() {
        return this.customerDto;
    }

}

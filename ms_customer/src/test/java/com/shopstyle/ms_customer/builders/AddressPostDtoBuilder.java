package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.web.dto.AddressPostDto;

public class AddressPostDtoBuilder {

    private AddressPostDto addressDto;

    public AddressPostDtoBuilder() {
    }

    public static AddressPostDtoBuilder anAddressPostDto() {
        AddressPostDtoBuilder builder = new AddressPostDtoBuilder();

        builder.addressDto = new AddressPostDto();
        builder.addressDto.setState("São Paulo");
        builder.addressDto.setCity("São Paulo");
        builder.addressDto.setDistrict("Centro");
        builder.addressDto.setStreet("Rua Principal");
        builder.addressDto.setNumber("123");
        builder.addressDto.setCep("01000-000");
        builder.addressDto.setComplement("Apto 101");

        return builder;
    }

    public AddressPostDtoBuilder withState(String state) {
        addressDto.setState(state);
        return this;
    }

    public AddressPostDtoBuilder withCity(String city) {
        addressDto.setCity(city);
        return this;
    }

    public AddressPostDtoBuilder withDistrict(String district) {
        addressDto.setDistrict(district);
        return this;
    }

    public AddressPostDtoBuilder withStreet(String street) {
        addressDto.setStreet(street);
        return this;
    }

    public AddressPostDtoBuilder withNumber(String number) {
        addressDto.setNumber(number);
        return this;
    }

    public AddressPostDtoBuilder withCep(String cep) {
        addressDto.setCep(cep);
        return this;
    }

    public AddressPostDtoBuilder withComplement(String complement) {
        addressDto.setComplement(complement);
        return this;
    }

    public AddressPostDtoBuilder withCustomer(Long customerId) {
        addressDto.setCustomer(customerId);
        return this;
    }

    public AddressPostDto get() {
        return this.addressDto;
    }
}

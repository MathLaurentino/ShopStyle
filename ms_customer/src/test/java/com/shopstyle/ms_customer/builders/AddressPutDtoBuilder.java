package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.web.dto.AddressPutDto;

public class AddressPutDtoBuilder {

    private AddressPutDto addressDto;

    public AddressPutDtoBuilder() {
    }

    public static AddressPutDtoBuilder anAddressPutDto() {
        AddressPutDtoBuilder builder = new AddressPutDtoBuilder();

        builder.addressDto = new AddressPutDto();
        builder.addressDto.setState("São Paulo");
        builder.addressDto.setCity("São Paulo");
        builder.addressDto.setDistrict("Centro");
        builder.addressDto.setStreet("Rua Principal");
        builder.addressDto.setNumber("123");
        builder.addressDto.setCep("01000-000");
        builder.addressDto.setComplement("Apto 101");

        return builder;
    }

    public AddressPutDtoBuilder withState(String state) {
        addressDto.setState(state);
        return this;
    }

    public AddressPutDtoBuilder withCity(String city) {
        addressDto.setCity(city);
        return this;
    }

    public AddressPutDtoBuilder withDistrict(String district) {
        addressDto.setDistrict(district);
        return this;
    }

    public AddressPutDtoBuilder withStreet(String street) {
        addressDto.setStreet(street);
        return this;
    }

    public AddressPutDtoBuilder withNumber(String number) {
        addressDto.setNumber(number);
        return this;
    }

    public AddressPutDtoBuilder withCep(String cep) {
        addressDto.setCep(cep);
        return this;
    }

    public AddressPutDtoBuilder withComplement(String complement) {
        addressDto.setComplement(complement);
        return this;
    }

    public AddressPutDto get() {
        return this.addressDto;
    }
}

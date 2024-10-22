package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.web.dto.AddressPostAndPutDto;

public class AddressPostAndPutDtoBuilder {

    private AddressPostAndPutDto addressDto;

    public AddressPostAndPutDtoBuilder() {
    }

    public static AddressPostAndPutDtoBuilder anAddressPostAndPutDto() {
        AddressPostAndPutDtoBuilder builder = new AddressPostAndPutDtoBuilder();

        builder.addressDto = new AddressPostAndPutDto();
        builder.addressDto.setState("São Paulo");
        builder.addressDto.setCity("São Paulo");
        builder.addressDto.setDistrict("Centro");
        builder.addressDto.setStreet("Rua Principal");
        builder.addressDto.setNumber("123");
        builder.addressDto.setCep("01000-000");
        builder.addressDto.setComplement("Apto 101");

        return builder;
    }

    public AddressPostAndPutDtoBuilder withState(String state) {
        addressDto.setState(state);
        return this;
    }

    public AddressPostAndPutDtoBuilder withCity(String city) {
        addressDto.setCity(city);
        return this;
    }

    public AddressPostAndPutDtoBuilder withDistrict(String district) {
        addressDto.setDistrict(district);
        return this;
    }

    public AddressPostAndPutDtoBuilder withStreet(String street) {
        addressDto.setStreet(street);
        return this;
    }

    public AddressPostAndPutDtoBuilder withNumber(String number) {
        addressDto.setNumber(number);
        return this;
    }

    public AddressPostAndPutDtoBuilder withCep(String cep) {
        addressDto.setCep(cep);
        return this;
    }

    public AddressPostAndPutDtoBuilder withComplement(String complement) {
        addressDto.setComplement(complement);
        return this;
    }

    public AddressPostAndPutDtoBuilder withCustomer(Long customerId) {
        addressDto.setCustomer(customerId);
        return this;
    }

    public AddressPostAndPutDto get() {
        return this.addressDto;
    }
}

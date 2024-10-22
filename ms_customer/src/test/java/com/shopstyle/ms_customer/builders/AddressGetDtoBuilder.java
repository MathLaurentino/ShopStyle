package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.web.dto.AddressGetDto;

public class AddressGetDtoBuilder {

    private AddressGetDto addressGetDto;

    public AddressGetDtoBuilder() {
    }

    public static AddressGetDtoBuilder anAddressGetDto() {
        AddressGetDtoBuilder builder = new AddressGetDtoBuilder();

        builder.addressGetDto = new AddressGetDto();
        builder.addressGetDto.setId(1L);
        builder.addressGetDto.setState("São Paulo");
        builder.addressGetDto.setCity("São Paulo");
        builder.addressGetDto.setDistrict("Centro");
        builder.addressGetDto.setStreet("Rua Principal");
        builder.addressGetDto.setNumber("123");
        builder.addressGetDto.setCep("01000-000");
        builder.addressGetDto.setComplement("Apto 101");
        builder.addressGetDto.setCustomer(1L);
        return builder;
    }

    public AddressGetDtoBuilder withId(Long id) {
        addressGetDto.setId(id);
        return this;
    }

    public AddressGetDtoBuilder withState(String state) {
        addressGetDto.setState(state);
        return this;
    }

    public AddressGetDtoBuilder withCity(String city) {
        addressGetDto.setCity(city);
        return this;
    }

    public AddressGetDtoBuilder withDistrict(String district) {
        addressGetDto.setDistrict(district);
        return this;
    }

    public AddressGetDtoBuilder withStreet(String street) {
        addressGetDto.setStreet(street);
        return this;
    }

    public AddressGetDtoBuilder withNumber(String number) {
        addressGetDto.setNumber(number);
        return this;
    }

    public AddressGetDtoBuilder withCep(String cep) {
        addressGetDto.setCep(cep);
        return this;
    }

    public AddressGetDtoBuilder withComplement(String complement) {
        addressGetDto.setComplement(complement);
        return this;
    }

    public AddressGetDtoBuilder withCustomer(Long customerId) {
        addressGetDto.setCustomer(customerId);
        return this;
    }

    public AddressGetDto get() {
        return this.addressGetDto;
    }
}

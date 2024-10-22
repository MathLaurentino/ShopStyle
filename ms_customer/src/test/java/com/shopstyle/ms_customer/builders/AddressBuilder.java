package com.shopstyle.ms_customer.builders;

import com.shopstyle.ms_customer.entity.Address;
import com.shopstyle.ms_customer.entity.Customer;

import static com.shopstyle.ms_customer.builders.CustomerBuilder.aCustomer;

public class AddressBuilder {

    private Address address;

    public AddressBuilder() {
    }

    public static AddressBuilder anAddress() {
        AddressBuilder builder = new AddressBuilder();

        builder.address = new Address();
        builder.address.setState("São Paulo");
        builder.address.setCity("São Paulo");
        builder.address.setDistrict("Centro");
        builder.address.setStreet("Rua Principal");
        builder.address.setNumber("123");
        builder.address.setCep("01000-000");
        builder.address.setComplement("Apto 101");
        builder.address.setCustomer(aCustomer().get());

        return builder;
    }

    public AddressBuilder withState(String state) {
        address.setState(state);
        return this;
    }

    public AddressBuilder withCity(String city) {
        address.setCity(city);
        return this;
    }

    public AddressBuilder withDistrict(String district) {
        address.setDistrict(district);
        return this;
    }

    public AddressBuilder withStreet(String street) {
        address.setStreet(street);
        return this;
    }

    public AddressBuilder withNumber(String number) {
        address.setNumber(number);
        return this;
    }

    public AddressBuilder withCep(String cep) {
        address.setCep(cep);
        return this;
    }

    public AddressBuilder withComplement(String complement) {
        address.setComplement(complement);
        return this;
    }

    public AddressBuilder withCustomer(Customer customer) {
        address.setCustomer(customer);
        return this;
    }

    public AddressBuilder withId(Long id) {
        address.setId(id);
        return this;
    }

    public Address get() {
        return this.address;
    }
}
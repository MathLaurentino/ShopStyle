package com.shopstyle.bff_shop.web.dto.ms_customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address implements Serializable {

    private Long id;
    private String state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String cep;
    private String complement;

}
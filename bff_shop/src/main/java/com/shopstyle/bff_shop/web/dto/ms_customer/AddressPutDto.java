package com.shopstyle.bff_shop.web.dto.ms_customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressPutDto {

    private String state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String cep;
    private String complement;

}
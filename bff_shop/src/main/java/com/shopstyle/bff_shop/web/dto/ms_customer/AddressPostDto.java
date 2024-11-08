package com.shopstyle.bff_shop.web.dto.ms_customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressPostDto {

    private String state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String cep;
    private String complement;
    @JsonProperty("customerId")
    private Long customer;

}
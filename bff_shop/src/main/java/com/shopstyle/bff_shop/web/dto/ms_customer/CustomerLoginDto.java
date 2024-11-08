package com.shopstyle.bff_shop.web.dto.ms_customer;

import lombok.Data;

@Data
public class CustomerLoginDto {

    private String email;
    private String password;

}
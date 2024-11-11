package com.shopstyle.bff_shop.web.dto.ms_customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPutDto {

    private String cpf;
    private String firstName;
    private String lastName;
    private String sex;
    private LocalDate birthdate;
    private String email;
    private String active;

}
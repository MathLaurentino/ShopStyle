package com.shopstyle.ms_customer.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerLoginDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

}

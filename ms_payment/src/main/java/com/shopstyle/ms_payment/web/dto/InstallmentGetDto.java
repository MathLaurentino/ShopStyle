package com.shopstyle.ms_payment.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentGetDto {

    private Long id;
    private Integer amount;
    private String brand;
    private Long paymentId;

}

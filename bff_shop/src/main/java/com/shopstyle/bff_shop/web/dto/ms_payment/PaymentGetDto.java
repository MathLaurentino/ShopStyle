package com.shopstyle.bff_shop.web.dto.ms_payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentGetDto {

    private Long id;
    private String type;
    private Boolean installments;
    private Boolean active;
    private Installment installment;

}
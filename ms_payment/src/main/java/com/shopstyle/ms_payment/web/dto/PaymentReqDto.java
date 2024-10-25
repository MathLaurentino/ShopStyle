package com.shopstyle.ms_payment.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDto {

    @NotBlank
    private String type;

    @NotNull
    private Boolean installment;

    @NotNull
    private Boolean active;

}

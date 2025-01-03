package com.shopstyle.ms_payment.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentReqDto {

    @NotNull
    @Min(2)
    private Integer amount;

    private String brand;

    @NotNull
    @Min(1)
    private Long paymentId;

}

package com.shopstyle.ms_order.web.dto;

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
public class PaymentDto {

    @NotNull
    @Min(1L)
    private Long id;

    @NotNull
    @Min(1)
    private Integer installments;

}
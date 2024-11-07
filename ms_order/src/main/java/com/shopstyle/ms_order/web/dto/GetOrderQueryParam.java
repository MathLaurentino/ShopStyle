package com.shopstyle.ms_order.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderQueryParam {

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;

    @Pattern(regexp = "PAYMENT_NOT_FOUND|PAYMENT_INACTIVE|PAYMENT_NOT_INSTALLMENT|PAYMENT_AMOUNT_NOT_AVAILABLE|PAYMENT_SUCCESSFUL|PROCESSING_PAYMENT")
    private String status;

}

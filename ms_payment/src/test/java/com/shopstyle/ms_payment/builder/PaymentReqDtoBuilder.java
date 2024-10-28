package com.shopstyle.ms_payment.builder;

import com.shopstyle.ms_payment.web.dto.PaymentReqDto;

public class PaymentReqDtoBuilder {

    private PaymentReqDto dto;

    public PaymentReqDtoBuilder() {
        dto = new PaymentReqDto();
        dto.setType("Credit Card");
        dto.setInstallments("true");
        dto.setActive("true");
    }

    public static PaymentReqDtoBuilder aPaymentReqDto() {
        return new PaymentReqDtoBuilder();
    }

    public PaymentReqDtoBuilder withType(String type) {
        dto.setType(type);
        return this;
    }

    public PaymentReqDtoBuilder withInstallments(String installments) {
        dto.setInstallments(installments);
        return this;
    }

    public PaymentReqDtoBuilder withActive(String active) {
        dto.setActive(active);
        return this;
    }

    public PaymentReqDto get() {
        return dto;
    }
}

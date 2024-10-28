package com.shopstyle.ms_payment.builder;

import com.shopstyle.ms_payment.web.dto.InstallmentReqDto;

public class InstallmentReqDtoBuilder {

    private InstallmentReqDto dto;

    public InstallmentReqDtoBuilder() {
        dto = new InstallmentReqDto();
        dto.setAmount(5);
        dto.setPaymentId(1L);
        dto.setBrand("Visa");
    }

    public static InstallmentReqDtoBuilder anInstallmentReqDto() {
        return new InstallmentReqDtoBuilder();
    }

    public InstallmentReqDtoBuilder withAmount(Integer amount) {
        dto.setAmount(amount);
        return this;
    }

    public InstallmentReqDtoBuilder withPaymentId(Long paymentId) {
        dto.setPaymentId(paymentId);
        return this;
    }

    public InstallmentReqDtoBuilder withBrand(String brand) {
        dto.setBrand(brand);
        return this;
    }

    public InstallmentReqDto get() {
        return dto;
    }
}

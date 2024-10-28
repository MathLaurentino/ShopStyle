package com.shopstyle.ms_payment.builder;

import com.shopstyle.ms_payment.entity.Payment;

public class PaymentBuilder {

    private Payment payment;

    public PaymentBuilder() {
        payment = new Payment();
        payment.setType("Credit Card");
        payment.setInstallments(true);
        payment.setActive(true);
    }

    public static PaymentBuilder aPayment() {
        return new PaymentBuilder();
    }

    public PaymentBuilder withId(Long id) {
        payment.setId(id);
        return this;
    }

    public PaymentBuilder withType(String type) {
        payment.setType(type);
        return this;
    }

    public PaymentBuilder withInstallments(Boolean installments) {
        payment.setInstallments(installments);
        return this;
    }

    public PaymentBuilder withActive(Boolean active) {
        payment.setActive(active);
        return this;
    }

    public Payment get() {
        return payment;
    }
}

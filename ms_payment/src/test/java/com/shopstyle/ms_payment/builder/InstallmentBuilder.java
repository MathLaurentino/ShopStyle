package com.shopstyle.ms_payment.builder;

import com.shopstyle.ms_payment.entity.Installment;
import com.shopstyle.ms_payment.entity.Payment;

public class InstallmentBuilder {

    private Installment installment;

    public InstallmentBuilder() {
        installment = new Installment();
        installment.setAmount(5);
        installment.setBrand("Visa");
    }

    public static InstallmentBuilder anInstallment() {
        return new InstallmentBuilder();
    }

    public InstallmentBuilder withId(Long id) {
        installment.setId(id);
        return this;
    }

    public InstallmentBuilder withAmount(Integer amount) {
        installment.setAmount(amount);
        return this;
    }

    public InstallmentBuilder withBrand(String brand) {
        installment.setBrand(brand);
        return this;
    }

    public InstallmentBuilder withPayment(Payment payment) {
        installment.setPayment(payment);
        return this;
    }

    public Installment get() {
        return installment;
    }
}

package com.shopstyle.ms_payment.exception;

public class PaymentAlreadyHaveAnInstallmentException extends RuntimeException{

    public PaymentAlreadyHaveAnInstallmentException(String message) {
        super(message);
    }

}

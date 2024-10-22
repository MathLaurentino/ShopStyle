package com.shopstyle.ms_customer.exception;

public class CpfAlreadyInUseException extends RuntimeException {
    public CpfAlreadyInUseException(String message) {
        super(message);
    }
}
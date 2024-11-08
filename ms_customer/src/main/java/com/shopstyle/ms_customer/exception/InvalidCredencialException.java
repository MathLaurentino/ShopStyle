package com.shopstyle.ms_customer.exception;

import lombok.Getter;

@Getter
public class InvalidCredencialException extends RuntimeException {

    private String username;

    public InvalidCredencialException(String username) {
        this.username = username;
    }
}
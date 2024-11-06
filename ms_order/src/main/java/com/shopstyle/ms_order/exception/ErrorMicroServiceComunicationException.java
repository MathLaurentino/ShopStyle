package com.shopstyle.ms_order.exception;

import lombok.Getter;

public class ErrorMicroServiceComunicationException extends RuntimeException{
    @Getter
    private Integer status;

    public ErrorMicroServiceComunicationException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}

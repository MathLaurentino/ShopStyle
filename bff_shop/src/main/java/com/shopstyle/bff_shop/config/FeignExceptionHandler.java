package com.shopstyle.bff_shop.config;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignStatusException(FeignException e) {
        HttpStatus status = HttpStatus.valueOf(e.status());
        String errorMessage = e.contentUTF8();
        return new ResponseEntity<>(errorMessage, status);
    }
}

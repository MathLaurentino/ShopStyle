package com.shopstyle.bff_shop.web.dto.ms_order;

public enum OrderStatus {

    PAYMENT_NOT_FOUND,
    PAYMENT_INACTIVE,
    PAYMENT_NOT_INSTALLMENT,
    PAYMENT_AMOUNT_NOT_AVAILABLE,
    PAYMENT_SUCCESSFUL,
    PROCESSING_PAYMENT

}
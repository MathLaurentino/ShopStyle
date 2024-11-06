package com.shopstyle.ms_audit.service;
public interface AuditConsumerService {

    void listenOrderPayments(String message);
    void listenPaymentStatus(String message);
    void listenCatalogSkus(String message);

}

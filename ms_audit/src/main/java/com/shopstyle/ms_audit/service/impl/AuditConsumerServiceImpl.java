package com.shopstyle.ms_audit.service.impl;

import com.shopstyle.ms_audit.entity.AuditRecord;
import com.shopstyle.ms_audit.repository.AuditRepository;
import com.shopstyle.ms_audit.service.AuditConsumerService;
import com.shopstyle.ms_audit.web.dto.CatalogSkusEvent;
import com.shopstyle.ms_audit.web.dto.OrderPaymentsEvent;
import com.shopstyle.ms_audit.web.dto.PaymentStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuditConsumerServiceImpl implements AuditConsumerService {

    private final AuditRepository auditRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "order-payments", groupId = "order-payments-group")
    public void listenOrderPayments(String message) {
        try {
            OrderPaymentsEvent event = objectMapper.readValue(message, OrderPaymentsEvent.class);

            Optional<AuditRecord> optional = auditRepository.findByOrderId(event.getOrderId());
            AuditRecord auditRecord;

            if (optional.isEmpty()) {
                auditRecord = new AuditRecord();
                auditRecord.setOrderId(event.getOrderId());
            } else {
                auditRecord = optional.get();
            }

            auditRecord.setPaymentInfo(event.getPayment());
            auditRepository.save(auditRecord);

            log.info("listenOrderPayments salvo para orderId: " + auditRecord.getOrderId());
        } catch (Exception e) {
            log.warn("Error with listenOrderPayments: \n" + e);
        }
    }

    @KafkaListener(topics = "payment-status", groupId = "payment-status-group")
    public void listenPaymentStatus(String message) {
        try {
            PaymentStatusEvent event = objectMapper.readValue(message, PaymentStatusEvent.class);
            Optional<AuditRecord> optional = auditRepository.findByOrderId(event.getOrderId());
            AuditRecord auditRecord;

            if (optional.isEmpty()) {
                auditRecord = new AuditRecord();
                auditRecord.setOrderId(event.getOrderId());
            } else {
                auditRecord = optional.get();
            }

            auditRecord.setPaymentStatus(event.getStatus());
            auditRepository.save(auditRecord);

            log.info("listenPaymentStatus salvo para orderId: " + auditRecord.getOrderId());
        } catch (Exception e) {
            log.warn("Error with listenPaymentStatus: \n" + e);
        }
    }

    @KafkaListener(topics = "catalog-skus", groupId = "catalog-skus-group")
    public void listenCatalogSkus(String message) {
        try {
            CatalogSkusEvent event = objectMapper.readValue(message, CatalogSkusEvent.class);
            Optional<AuditRecord> optional = auditRepository.findByOrderId(event.getOrderId());
            AuditRecord auditRecord;

            if (optional.isEmpty()) {
                auditRecord = new AuditRecord();
                auditRecord.setOrderId(event.getOrderId());
            } else {
                auditRecord = optional.get();
            }

            auditRecord.setSkus(event.getSkus());
            auditRepository.save(auditRecord);

            log.info("listenCatalogSkus salvo para orderId: " + auditRecord.getOrderId());
        } catch (Exception e) {
            log.warn("Error with listenCatalogSkus: \n" + e);
        }
    }
}

package com.shopstyle.ms_audit.web.controller;


import com.shopstyle.ms_audit.entity.AuditRecord;
import org.springframework.http.ResponseEntity;

public interface AuditController {

    ResponseEntity<AuditRecord> getAuditByOrderId(String orderId);

}

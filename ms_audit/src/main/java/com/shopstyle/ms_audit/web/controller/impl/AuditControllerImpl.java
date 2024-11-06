package com.shopstyle.ms_audit.web.controller.impl;

import com.shopstyle.ms_audit.entity.AuditRecord;
import com.shopstyle.ms_audit.service.AuditService;
import com.shopstyle.ms_audit.web.controller.AuditController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/audit/orders")
public class AuditControllerImpl implements AuditController {

    private final AuditService service;

    @Override
    @GetMapping("/{orderId}")
    public ResponseEntity<AuditRecord> getAuditByOrderId(@PathVariable String orderId) {
        AuditRecord auditRecord = service.findAuditByOrderId(orderId);
        return ResponseEntity.ok(auditRecord);
    }

}

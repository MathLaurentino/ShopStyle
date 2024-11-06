package com.shopstyle.ms_audit.service;

import com.shopstyle.ms_audit.entity.AuditRecord;

public interface AuditService {

    AuditRecord findAuditByOrderId(String id);

}

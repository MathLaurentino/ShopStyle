package com.shopstyle.ms_audit.repository;

import com.shopstyle.ms_audit.entity.AuditRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuditRepository extends MongoRepository<AuditRecord, String> {

    Optional<AuditRecord> findByOrderId(String orderId);

}


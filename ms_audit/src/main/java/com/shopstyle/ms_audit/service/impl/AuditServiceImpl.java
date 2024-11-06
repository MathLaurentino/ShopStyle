package com.shopstyle.ms_audit.service.impl;

import com.shopstyle.ms_audit.entity.AuditRecord;
import com.shopstyle.ms_audit.exception.EntityNotFoundException;
import com.shopstyle.ms_audit.repository.AuditRepository;
import com.shopstyle.ms_audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository repository;

    @Override
    public AuditRecord findAuditByOrderId(String id) {
        return repository.findByOrderId(id).orElseThrow(
                () -> new EntityNotFoundException("não encontrado"));

    }

}

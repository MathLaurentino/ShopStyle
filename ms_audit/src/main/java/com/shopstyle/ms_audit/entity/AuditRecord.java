package com.shopstyle.ms_audit.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"orderId"})
@Document(collection = "audit_records")
public class AuditRecord {
    
    @Id
    private String id;
    
    private String orderId;
    private PaymentInfo paymentInfo;
    private String paymentStatus;
    private List<SkuInfo> skus;

}
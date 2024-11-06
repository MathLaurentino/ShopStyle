package com.shopstyle.ms_audit.web.dto;

import com.shopstyle.ms_audit.entity.SkuInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatalogSkusEvent {

    private String orderId;
    private List<SkuInfo> skus;

}
package com.shopstyle.ms_catalog.service.impl;

import com.shopstyle.ms_catalog.service.CatalogSkusConsumerService;
import com.shopstyle.ms_catalog.service.SkuService;
import com.shopstyle.ms_catalog.web.dto.kafka.SkusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogSkusConsumerServiceImpl implements CatalogSkusConsumerService {

    private final SkuService service;

    @Override
    @KafkaListener(topics = "catalog-skus", groupId = "payment-group", containerFactory = "jsonContainerFactory")
    public void consumeCatalogSkusMessage(SkusMessage message) {
        log.info("ms-catalog consume message, orderId: " + message.getOrderId());
        service.decreaseSkuQuantity(message.getSkus());
    }

}

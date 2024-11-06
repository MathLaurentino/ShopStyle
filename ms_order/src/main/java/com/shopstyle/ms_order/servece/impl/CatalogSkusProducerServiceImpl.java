package com.shopstyle.ms_order.servece.impl;

import com.shopstyle.ms_order.servece.CatalogSkusProducerService;
import com.shopstyle.ms_order.web.dto.kafka.SkusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogSkusProducerServiceImpl implements CatalogSkusProducerService {

    private final KafkaTemplate<String, SkusMessage> kafkaTemplate;

    @Override
    public void sendSkusMessage(SkusMessage message) {
        log.info("Mensagem ao catalog-skus, orderId: " + message.getOrderId());
        kafkaTemplate.send("catalog-skus", message);
    }
}

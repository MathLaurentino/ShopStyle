package com.shopstyle.ms_catalog.service;

import com.shopstyle.ms_catalog.web.dto.kafka.SkusMessage;

public interface CatalogSkusConsumerService {

    void consumeCatalogSkusMessage(SkusMessage message);

}

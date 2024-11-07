package com.shopstyle.ms_order.kafka;

import com.shopstyle.ms_order.web.dto.kafka.SkusMessage;

public interface CatalogSkusProducerService {

    void sendSkusMessage(SkusMessage message);

}

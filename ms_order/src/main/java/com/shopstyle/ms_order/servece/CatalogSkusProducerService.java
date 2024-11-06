package com.shopstyle.ms_order.servece;

import com.shopstyle.ms_order.web.dto.kafka.SkusMessage;

public interface CatalogSkusProducerService {

    void sendSkusMessage(SkusMessage message);

}

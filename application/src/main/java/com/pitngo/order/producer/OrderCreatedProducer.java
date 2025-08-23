package com.pitngo.order.producer;

import com.pitngo.order.model.OrderCreatedEvent;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderCreatedProducer {
    private final StreamBridge streamBridge;

    public OrderCreatedProducer(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public void send(OrderCreatedEvent event) {
        streamBridge.send("orderCreated-out-0", MessageBuilder.withPayload(event).build());
    }
}

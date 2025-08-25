package com.pitngo.order.consumer;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.pitngo.order.Redis.OrderStateRepository;
import com.pitngo.order.Redis.RedisStateService;
import com.pitngo.order.model.OrderCreatedEvent;

@Component
class OrderCreatedConsumer {
    private static final Logger log = LoggerFactory.getLogger(OrderCreatedConsumer.class);
    private final OrderStateRepository orderStateRepository;

    @Autowired
    public OrderCreatedConsumer(OrderStateRepository orderStateRepository) {
        this.orderStateRepository = orderStateRepository;
    }

    @Bean
    public Consumer<OrderCreatedEvent> orderCreated() {
        return event -> {
            log.info("📥 Received order event: {}", event);
            orderStateRepository.updateStats(event);
        };
    }
}

package com.pitngo.order.Redis;

import java.util.Map;

import com.pitngo.order.model.OrderCreatedEvent;

public interface OrderStateRepository {
        void updateStats(OrderCreatedEvent orderCreatedEvent);
        Map<String, Object> getStatsForUser(String userId);
}

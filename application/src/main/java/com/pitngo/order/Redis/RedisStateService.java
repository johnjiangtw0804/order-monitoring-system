package com.pitngo.order.Redis;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.pitngo.order.model.OrderCreatedEvent;

@Service
public class RedisStateService implements OrderStateRepository{
    private static final Logger log = LoggerFactory.getLogger(OrderStateRepository.class);
    private final RedisTemplate<String, String> redisTemplate;

    public RedisStateService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void updateStats(OrderCreatedEvent orderCreatedEvent) {
        String userId = orderCreatedEvent.getUserId();
        String keyUserCount = "orders:users:" + userId + ":count";
        Long result = redisTemplate.opsForValue().increment(keyUserCount, 1);
        log.debug("user order count key updated: {} = {}", keyUserCount, result);

        String keyTotalCount = "orders:total";
        redisTemplate.opsForValue().increment(keyTotalCount, 1);

        String keyLatestOrder = "orders:users:" + userId + ":latest";
        redisTemplate.opsForHash().put(keyLatestOrder, "description", orderCreatedEvent.getDescription());
    }

    public Map<String, Object> getStatsForUser(String userId) {
        Map<String, Object> stats = new HashMap<>();
        String keyUserCount = "orders:users:" + userId + ":count";
        String keyLatestOrder = "orders:users:" + userId + ":latest";

        String count = redisTemplate.opsForValue().get(keyUserCount);
        Map<Object, Object> latestOrder = redisTemplate.opsForHash().entries(keyLatestOrder);

        stats.put("totalOrders", count != null ? Integer.parseInt(count) : 0);
        stats.put("latestOrder", latestOrder);

        return stats;
    }
}

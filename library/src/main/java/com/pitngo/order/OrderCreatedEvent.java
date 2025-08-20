package com.pitngo.order;

import java.time.Instant;

public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private double amount;
    private Instant createdAt;
}

package com.pitngo.order.model;

import java.time.Instant;

public class OrderCreatedEvent {
    private String orderId;
    private String userId;
    private double amount;
    private Instant createdAt;
    private String description;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(String orderId, String userId, double amount, Instant createdAt, String description) {
        this.orderId = orderId;
        this.userId = userId;
        this.amount = amount;
        this.createdAt = createdAt;
        this.description = description;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getUserId() {
        return this.userId;
    }

    public double getAmount() {
        return this.amount;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

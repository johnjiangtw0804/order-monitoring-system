package com.pitngo.order.controller;

import com.pitngo.order.model.OrderCreatedEvent;
import com.pitngo.order.producer.OrderCreatedProducer;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderCreatedProducer producer;

    public OrderController(OrderCreatedProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderCreatedEvent order) {
        order.setCreatedAt(Instant.now());
        producer.send(order);
        return "Order sent!";
    }
}

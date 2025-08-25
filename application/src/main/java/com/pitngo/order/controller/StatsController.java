package com.pitngo.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitngo.order.Redis.OrderStateRepository;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/stats")
public class StatsController {
    private final OrderStateRepository orderStateRepository;

    public StatsController(OrderStateRepository orderStateRepository) {
        this.orderStateRepository = orderStateRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> getMethodName(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(orderStateRepository.getStatsForUser(userId));
    }
}

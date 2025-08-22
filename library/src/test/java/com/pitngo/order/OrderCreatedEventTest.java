package com.pitngo.order;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.pitngo.order.model.OrderCreatedEvent;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderCreatedEventTest {
    private final ObjectMapper objectMapper = JsonMapper.builder()
    .addModule(new ParameterNamesModule())
    .addModule(new Jdk8Module())
    .addModule(new JavaTimeModule())
    .build();
  @Test
    void testJacksonSerializationDeserialization() throws Exception {
        OrderCreatedEvent original = new OrderCreatedEvent(
                "order-123",
                "user-abc",
                99.99,
                Instant.parse("2025-08-19T10:00:00Z"), // 2025-08-19 Time 10:00:00 Z(UTC)
                "Flash sale order"
        );

        String json = objectMapper.writeValueAsString(original);
        System.out.println("Serialized JSON: " + json);

        OrderCreatedEvent deserialized = objectMapper.readValue(json, OrderCreatedEvent.class);

        assertThat(deserialized.getOrderId()).isEqualTo("order-123");
        assertThat(deserialized.getUserId()).isEqualTo("user-abc");
        assertThat(deserialized.getAmount()).isEqualTo(99.99);
        assertThat(deserialized.getCreatedAt()).isEqualTo(Instant.parse("2025-08-19T10:00:00Z"));
        assertThat(deserialized.getDescription()).isEqualTo("Flash sale order");
    }
}

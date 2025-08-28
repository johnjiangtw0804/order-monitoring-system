package com.pitngo.order.Dynamo.Repository;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.pitngo.order.Dynamo.Model.UserStats;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class UserStatsRepository {
    private final DynamoDbEnhancedClient ec;

    public UserStatsRepository(DynamoDbEnhancedClient ec) {
        this.ec = ec;
    }

    public void incrementStats(String userId, double amount, Instant now) {
        DynamoDbTable<UserStats> table = ec.table("UserStats",
                                              TableSchema.fromBean(UserStats.class));
        var key = Key.builder().partitionValue(userId).build();

        UserStats stats = table.getItem(r -> r.key(key));
        if (stats == null) {
            stats = new UserStats();
            stats.setUserId(userId);
            stats.setOrderCount(0);
            stats.setTotalAmount(0.0);
        }

        stats.setOrderCount(stats.getOrderCount() + 1);
        stats.setTotalAmount(stats.getTotalAmount() + amount);
        stats.setUpdatedAt(now.toString());

        table.updateItem(stats);
    }
}

package com.pitngo.order.Dynamo.Repository;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.pitngo.order.Dynamo.Model.UserStats;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@Service
public class UserStatsRepository {
    private final DynamoDbTable<UserStats> table;

    public UserStatsRepository(DynamoDbTable<UserStats> table) {
        this.table = table;
    }

    public void incrementStats(String userId, double amount, Instant now) {
        var updateExpr = Expression.builder()
                .expression("SET #oc = if_not_exists(#oc, :zero) + :one, " +
                        "#ta = if_not_exists(#ta, :zero) + :amt, " +
                        "#ua = :ts")
                .putExpressionName("#oc", "orderCount")
                .putExpressionName("#ta", "totalAmount")
                .putExpressionName("#ua", "updatedAt")
                .putExpressionValue(":zero", AttributeValue.builder().n("0").build())
                .putExpressionValue(":one", AttributeValue.builder().n("1").build())
                .putExpressionValue(":amt", AttributeValue.builder().n(String.valueOf(amount)).build())
                .putExpressionValue(":ts", AttributeValue.builder().s(now.toString()).build())
                .build();

        var key = Key.builder().partitionValue(userId).build();

        table.updateItem(r -> r
                .key(key)
                .updateExpression(updateExpr)
                .ignoreNulls(true) // 只更新表達式的欄位
        );
    }
}

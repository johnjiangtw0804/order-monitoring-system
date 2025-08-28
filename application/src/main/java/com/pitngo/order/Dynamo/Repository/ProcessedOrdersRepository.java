package com.pitngo.order.Dynamo.Repository;

import org.springframework.stereotype.Service;

import com.pitngo.order.Dynamo.Model.ProcessedOrder;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Expression;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ConditionalCheckFailedException;

@Service
public class ProcessedOrdersRepository {
    private final DynamoDbEnhancedClient ec;

    public ProcessedOrdersRepository(DynamoDbEnhancedClient ec) {
        this.ec = ec;
    }

    public boolean tryMarkProcessed(String orderId) {
        DynamoDbTable<ProcessedOrder> table = ec.table("ProcessedOrders",
                                              TableSchema.fromBean(ProcessedOrder.class));
        ProcessedOrder item = new ProcessedOrder();
        item.setOrderId(orderId);

        var cond = Expression.builder()
            .expression("attribute_not_exists(#id)")
            .putExpressionName("#id", "orderId")
            .build();

        try {
            table.putItem(r -> r.item(item).conditionExpression(cond));
            return true;  // 首次
        } catch (ConditionalCheckFailedException e) {
            return false; // 已存在
        }
    }
}

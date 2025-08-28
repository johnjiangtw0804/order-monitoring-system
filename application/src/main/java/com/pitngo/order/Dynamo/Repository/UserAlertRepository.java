package com.pitngo.order.Dynamo.Repository;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.pitngo.order.Dynamo.Model.ProcessedOrder;
import com.pitngo.order.Dynamo.Model.UserAlert;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class UserAlertRepository {
    private final DynamoDbEnhancedClient ec;

    public UserAlertRepository(DynamoDbEnhancedClient ec) {
        this.ec = ec;
    }

    public void putHighAmount(String userId, double amount, Instant at) {
        DynamoDbTable<UserAlert> table = ec.table("UserAlert",
                                        TableSchema.fromBean(UserAlert.class));
        var a = new UserAlert();
        a.setUserId(userId);
        a.setAlertTime(at.toString());
        a.setType("HIGH_AMOUNT");
        a.setAmount(amount);
        a.setMessage("High amount order: %.2f at %s".formatted(amount, at));

        table.putItem(a);
    }
}

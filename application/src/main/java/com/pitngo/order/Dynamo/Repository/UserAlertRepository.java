package com.pitngo.order.Dynamo.Repository;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.pitngo.order.Dynamo.Model.UserAlert;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Service
public class UserAlertRepository {
    private final DynamoDbTable<UserAlert> table;

    public UserAlertRepository(DynamoDbTable<UserAlert> table) {
        this.table = table;
    }

    public void putHighAmount(String userId, double amount, Instant at) {
        var a = new UserAlert();
        a.setUserId(userId);
        a.setAlertTime(at.toString());
        a.setType("HIGH_AMOUNT");
        a.setAmount(amount);
        a.setMessage("High amount order: %.2f at %s".formatted(amount, at));

        table.putItem(r->r.item(a));
    }
}

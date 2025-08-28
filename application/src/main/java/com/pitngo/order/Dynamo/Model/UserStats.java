package com.pitngo.order.Dynamo.Model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class UserStats {
    private String userId;
    private Integer orderCount;
    private Double totalAmount;
    private String updatedAt;

    @DynamoDbPartitionKey
    public String getUserId() {
        return userId;
    }

    public void setUserId(String v) {
        this.userId = v;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer v) {
        this.orderCount = v;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double v) {
        this.totalAmount = v;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String v) {
        this.updatedAt = v;
    }
}

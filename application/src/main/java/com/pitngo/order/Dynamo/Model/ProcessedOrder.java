package com.pitngo.order.Dynamo.Model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ProcessedOrder {
    private String orderId;
    private Long updatedAt;
    private String workerId;

    @DynamoDbPartitionKey
    public String getOrderId(){
        return orderId;
    }

    public void setOrderId(String v){
        this.orderId=v;
    }

    public Long getUpdatedAt() {
        return this.updatedAt;
    }

    public String getWorkerId() {
        return this.workerId;
    }
}

package com.pitngo.order.Dynamo.Model;

import com.pitngo.order.Dynamo.Model.util.ProcessedOrderStatus;

public class Order {
    private String orderId;
    private ProcessedOrderStatus status; // IN_PROGRESS / PROCESSED / FAILED
    private Long updatedAt; // epoch seconds
    private Integer attempts;
    private String workerId;
    private Long leaseUntil; // epoch seconds (租約到期)
}

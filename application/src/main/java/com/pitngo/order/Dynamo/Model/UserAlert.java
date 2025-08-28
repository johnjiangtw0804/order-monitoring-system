package com.pitngo.order.Dynamo.Model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

    @DynamoDbBean
public class UserAlert {
  private String userId;     // PK
  private String alertTime;  // SK
  private String type;
  private Double amount;
  private String message;

  @DynamoDbPartitionKey
  public String getUserId(){ return userId; }

  public void setUserId(String v){ this.userId = v; }

  @DynamoDbSortKey
  public String getAlertTime(){ return alertTime; }

  public void setAlertTime(String v){ this.alertTime = v; }
  public String getType(){ return type; }
  public void setType(String v){ this.type = v; }
  public Double getAmount(){ return amount; }
  public void setAmount(Double v){ this.amount = v; }
  public String getMessage(){ return message; }
  public void setMessage(String v){ this.message = v; }
}

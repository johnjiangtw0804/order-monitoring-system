package com.pitngo.order.Dynamo;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoConfig {

    @Bean
    DynamoDbClient dynamoDbClient(
            @Value("${app.aws.region}") String region,
            @Value("${app.dynamodb.endpoint}") String endpoint) {

        return DynamoDbClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint)) // 指向 DynamoDB Local
                .build();
    }

    @Bean
    DynamoDbEnhancedClient enhanced(DynamoDbClient dynamo) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamo)
                .build();
    }
}

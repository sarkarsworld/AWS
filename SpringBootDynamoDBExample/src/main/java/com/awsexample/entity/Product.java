package com.awsexample.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data // Keep this for other fields
@DynamoDbBean
public class Product {

    //@Getter(onMethod_ = {@DynamoDbPartitionKey})
    //@Getter(onMethod = @__({@DynamoDbPartitionKey}))

    /**
     * Lombok Version: If your Lombok version in pom.xml is very old (pre-1.16.x), it won't recognize onMethod_.
     */
    private String id; // No Lombok annotation here

    private String name;
    private int price;

    @DynamoDbPartitionKey // Manually place it on the getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

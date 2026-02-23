package com.awsexample.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Customer {

    private String custId;
    private String fName;
    private String lName;
    private String email;

    public Customer() {
    }

    public Customer(String custId, String fName, String lName, String email) {
        this.custId = custId;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
    }

    @DynamoDbPartitionKey
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

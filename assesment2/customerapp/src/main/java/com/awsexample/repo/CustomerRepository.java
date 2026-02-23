package com.awsexample.repo;

import com.awsexample.entity.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    private DynamoDbTable<Customer> customerTable;

    @PostConstruct
    public void init(){
        customerTable = dynamoDbEnhancedClient.table("customer", TableSchema.fromBean(Customer.class));
    }

    public Customer save(Customer customer) {
        customerTable.putItem(customer);
        return customer;
    }

    public List<Customer> findAll() {
        List<Customer> productList = new ArrayList<>();
        customerTable.scan().items().forEach(productList::add);
        return productList;
    }

    public Customer findById(String custId) {

        return customerTable.getItem(r -> r.key(k -> k.partitionValue(custId)));
    }


}

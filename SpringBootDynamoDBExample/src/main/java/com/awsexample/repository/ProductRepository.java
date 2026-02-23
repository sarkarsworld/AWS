package com.awsexample.repository;

import com.awsexample.entity.Product;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
@AllArgsConstructor
public class ProductRepository {

    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<Product> productTable;

    @PostConstruct
    public void init(){
        productTable = dynamoDbEnhancedClient.table("Product", TableSchema.fromBean(Product.class));
    }

    public Product save(Product product) {
        productTable.putItem(product);
        return product;
    }

    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        productTable.scan().items().forEach(productList::add);
        return productList;
    }

    public Product findById(String id) {
        return productTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }
}

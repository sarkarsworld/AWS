package com.awsexample.service;

import com.awsexample.entity.Product;
import com.awsexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }
}

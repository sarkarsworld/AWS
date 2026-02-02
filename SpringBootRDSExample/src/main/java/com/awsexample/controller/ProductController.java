package com.awsexample.controller;

import com.awsexample.entity.Product;
import com.awsexample.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/allproducts")
    public ResponseEntity getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/addproduct")
    public ResponseEntity saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity(savedProduct, HttpStatus.CREATED);
    }

}

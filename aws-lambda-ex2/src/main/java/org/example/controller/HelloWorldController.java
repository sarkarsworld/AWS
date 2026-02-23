package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping
    public String hello(){
        System.out.println("Hello World from simple spring boot rest api.");
        return "Hello World from simple spring boot rest api.";
    }
}

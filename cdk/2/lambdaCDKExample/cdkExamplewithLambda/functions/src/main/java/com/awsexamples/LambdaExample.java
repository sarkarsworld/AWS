package com.awsexamples;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaExample implements RequestHandler<String, String> {
    @Override
    public String handleRequest(String user, Context context) {
        return "Hello " + user;
    }
}

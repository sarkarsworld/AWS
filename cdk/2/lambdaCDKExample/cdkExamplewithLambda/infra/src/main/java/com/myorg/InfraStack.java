package com.myorg;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;

import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;


public class InfraStack extends Stack {
    public InfraStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public InfraStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        // The code that defines your stack goes here

        // example resource
        // final Queue queue = Queue.Builder.create(this, "InfraQueue")
        //         .visibilityTimeout(Duration.seconds(300))
        //         .build();


        // Creating a Lambda function
        Function.Builder.create(this, "cdk-simplelambda-example")
                .runtime(Runtime.JAVA_17)
                .handler("com.awsexamples.LambdaExample")
                .memorySize(128)
                .timeout(Duration.seconds(20))
                .functionName("cdksimplelambdaexample")
                .code(Code.fromAsset("../assets/function.jar"))
                .build();
    }
}

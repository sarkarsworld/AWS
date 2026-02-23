package com.awsexamples;

import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.SendCommandRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class S3ToEc2Deployer implements RequestHandler<S3Event, String> {

    private final SsmClient ssmClient = SsmClient.builder().build();

    @Override
    public String handleRequest(S3Event s3event, Context context) {
        // 1. Get S3 details
        S3EventNotification.S3EventNotificationRecord record = s3event.getRecords().get(0);
        String bucketName = record.getS3().getBucket().getName();
        String jarKey = record.getS3().getObject().getKey();

        // 2. Deployment Configuration (Use Environment Variables in production)
        String instanceId = System.getenv("EC2_INSTANCE_ID");
        String deployPath = "/home/ec2-user/app";

        context.getLogger().log("Deployment triggered for: " + jarKey);

        // 3. Define Shell Commands for EC2
        List<String> commands = Arrays.asList(
                "mkdir -p " + deployPath,
                "aws s3 cp s3://" + bucketName + "/" + jarKey + " " + deployPath + "/app.jar",
                "pkill -f 'java -jar' || true",
                "nohup java -jar " + deployPath + "/app.jar > " + deployPath + "/output.log 2>&1 &"
        );

        // 4. Send Command via SSM
        try {
            SendCommandRequest commandRequest = SendCommandRequest.builder()
                    .instanceIds(instanceId)
                    .documentName("AWS-RunShellScript")
                    .parameters(Collections.singletonMap("commands", commands))
                    .build();

            ssmClient.sendCommand(commandRequest);
            return "Successfully sent deployment command to " + instanceId;

        } catch (Exception e) {
            context.getLogger().log("Error: " + e.getMessage());
            return "Deployment failed.";
        }
    }
}

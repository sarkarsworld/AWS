package org.example;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
 
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;
 
public class CreateEC2 {
    public static void main(String[] args) {
        Ec2Client ec2Client = Ec2Client.builder().build();
//[group is empty]---->security group was not passed.
        //VPC---->multipels-->aws configuration for IAM user with different account
       String amiid = "ami-03a6eaae9938c858c";
 
        String instanceType = "t3.micro";
        String keyPairName = "mykeypair";
        String secGroupId = "sg-0f9720178763b6e28";
        String instanceName = "myinstance";
      /* String userDataScript = """
                #!/bin/bash
                set -e
                yum update -y
                yum install -y java-17-amazon-corretto awscli
                wsget s3://amzn-s3-chandra-1/SpringBootBasic-0.0.1-SNAPSHOT.jar $APP_DIR/app.jar
 
                """;*/
       /* String userDataBase64 = Base64.getEncoder()
                .encodeToString(userDataScript.getBytes(StandardCharsets.UTF_8));*/
 
        try {
            RunInstancesRequest runInstancesRequest = RunInstancesRequest.builder()
                    .imageId(amiid)
                    .instanceType(instanceType)
                    .keyName(keyPairName)
                    .securityGroupIds(secGroupId)
                    .maxCount(1)
                    .minCount(1)
                    .tagSpecifications(TagSpecification.builder()
                            .resourceType(ResourceType.INSTANCE)
                            .tags(Tag.builder()
                                    .key("Name")
                                    .value(instanceName)
                                    .build())
                            .build())//.userData(userDataBase64)
                    .build();
//all the resources to be provisioned in same region
            RunInstancesResponse response = ec2Client.runInstances(runInstancesRequest);
 
            String instanceId = response.instances().get(0).instanceId();
            System.out.println("EC2 Instance With Id : " + instanceId + " is launcing");
 
            ec2Client.close();
 
 
        }catch (Ec2Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
 
 
 
    }
}
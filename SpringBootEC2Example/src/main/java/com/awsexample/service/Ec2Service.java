package com.awsexample.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.ec2.Ec2Client;
import software.amazon.awssdk.services.ec2.model.*;

@Service
public class Ec2Service {

    private final Ec2Client ec2Client;

    public Ec2Service(Ec2Client ec2Client) {
        this.ec2Client = ec2Client;
    }

    public String createInstance(String amiId, String instanceName) {
        // 1. Build the request to launch the instance
        RunInstancesRequest runRequest = RunInstancesRequest.builder()
                .imageId(amiId) // e.g., "ami-0c55b159cbfafe1f0" (Amazon Linux 2)
                .instanceType(InstanceType.T3_MICRO)
                .securityGroups("Dev-SecurityGrp-SpringBoot") // By default, the instance will use the default security group, so provide our security group.
                .maxCount(1)
                .minCount(1)
                .keyName("Dev-KeyPair-SpringBoot") // Ensure this key exists in AWS
                .build();

        RunInstancesResponse response = ec2Client.runInstances(runRequest);
        String instanceId = response.instances().get(0).instanceId();

        // 2. Tag the instance with a name for easy identification
        Tag tag = Tag.builder()
                .key("Name")    // This exact key is what AWS Console looks for
                .value(instanceName)
                .build();

        CreateTagsRequest tagRequest = CreateTagsRequest.builder()
                .resources(instanceId)
                .tags(tag)
                .build();

        // 3. Apply the tag to the instance
        ec2Client.createTags(tagRequest);

        return instanceId;
    }
}

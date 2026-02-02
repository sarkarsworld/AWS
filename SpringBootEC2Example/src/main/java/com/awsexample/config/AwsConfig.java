package com.awsexample.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ec2.Ec2Client;

@Configuration
public class AwsConfig {

    @Value("${aws.accessKeyId}")
    private String accessKey;

    @Value("${aws.secretAccessKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Bean
    public Ec2Client ec2Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return Ec2Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}

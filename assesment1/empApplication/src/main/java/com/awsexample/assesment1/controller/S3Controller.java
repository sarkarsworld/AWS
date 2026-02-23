package com.awsexample.assesment1.controller;

import com.awsexample.assesment1.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    @Profile(value = "dev")
    public ResponseEntity<String> upload(
            @RequestParam("file") MultipartFile file,
            @Value("${cloud.aws.s3bucketName}") String s3bucketName
    ) throws IOException {
        s3Service.uploadFile(file, s3bucketName);
        return ResponseEntity.ok("File Uploaded Successfully");
    }

}

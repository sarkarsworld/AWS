package org.example;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import java.nio.file.Paths;
 
public class UploadFile {
 
    public static void main(String[] args) {
 
        S3Client s3Client = S3Client.builder().build();
 
        String bucketName = "amzn-bucket-chandra-2";
 
        String objectKey = "SpringBootaplication.jar";
 
        String localPath = "C:\\Users\\chandrakanta.pandey\\eclipse-workspace\\SpringBootBasic\\target\\SpringBootBasic-0.0.1-SNAPSHOT.jar";
 
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
 
        try {
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest, Paths.get(localPath));
            System.out.println("File Uploaded Successfull. Etag : " + putObjectResponse.eTag());
        }catch (S3Exception e) {
            System.err.println("Error : " + e.getMessage());
        }finally {
            s3Client.close();
        }
 
 
    }
 
}
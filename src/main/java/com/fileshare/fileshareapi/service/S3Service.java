package com.fileshare.fileshareapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final  S3Client s3Client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public String  upload(MultipartFile multipartFile) throws IOException{
        String objectPath = multipartFile.getOriginalFilename();
        PutObjectRequest fileObj = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(multipartFile.getOriginalFilename())
                .build();
        s3Client.putObject(fileObj , RequestBody.fromBytes(multipartFile.getBytes()));

        return objectPath;
    }

    public byte[] retrieve(String fileName){
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        ResponseBytes<GetObjectResponse> response = s3Client.getObjectAsBytes(request);
        return response.asByteArray();
    }
}

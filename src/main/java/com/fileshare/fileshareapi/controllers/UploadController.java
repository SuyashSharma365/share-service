package com.fileshare.fileshareapi.controllers;


import com.fileshare.fileshareapi.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController()
@RequiredArgsConstructor
public class UploadController {

    private final S3Service s3Service;

    @PostMapping("/v1/upload")
    public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        try{
            s3Service.upload(multipartFile);
            return ResponseEntity.ok(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

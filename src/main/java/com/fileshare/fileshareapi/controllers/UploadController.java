package com.fileshare.fileshareapi.controllers;


import com.fileshare.fileshareapi.model.UploadResponseDto;
import com.fileshare.fileshareapi.service.ShareService;
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

    private final ShareService shareService;

    @PostMapping("/api/upload")
    public ResponseEntity<UploadResponseDto> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String id =  shareService.upload(multipartFile);
        return ResponseEntity.ok(UploadResponseDto.builder().Key(id).build());
    }

}

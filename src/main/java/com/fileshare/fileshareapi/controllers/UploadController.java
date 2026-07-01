package com.fileshare.fileshareapi.controllers;


import com.fileshare.fileshareapi.model.RetrieveResponseDto;
import com.fileshare.fileshareapi.model.UploadResponseDto;
import com.fileshare.fileshareapi.service.MessageService;
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
    private final MessageService messageService;

    @PostMapping("/api/upload")
    public ResponseEntity<?> upload(@RequestParam(value = "file" , required = false) MultipartFile multipartFile, @RequestParam(value = "message" , required = false) String message) throws IOException {


        if(multipartFile == null && (message == null || message.isBlank())){
            return ResponseEntity.badRequest().body("Nothing send");
        }
        if((message == null || message.isBlank()) && multipartFile != null ) {
            String id = shareService.upload(multipartFile);
            return ResponseEntity.ok(UploadResponseDto.builder().Key(id).build());
        }

        if((message != null && !message.isBlank()) && multipartFile == null ) {
            String id = messageService.save(RetrieveResponseDto.builder().message(message).build());
            return ResponseEntity.ok(UploadResponseDto.builder().Key(id).build());
        }

        if((message != null && !message.isBlank()) && multipartFile != null ) {
            String id = messageService.saveBoth(RetrieveResponseDto.builder().message(message).build(), multipartFile);
            return ResponseEntity.ok(UploadResponseDto.builder().Key(id).build());
        }

        return ResponseEntity.badRequest().body("Nothing send");

    }

}

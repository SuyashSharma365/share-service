package com.fileshare.fileshareapi.controllers;


import com.fileshare.fileshareapi.Entity.MessageEntity;
import com.fileshare.fileshareapi.service.MessageService;
import com.fileshare.fileshareapi.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/api")
public class RetrieveController {


    private final S3Service s3Service;
    private final MessageService messageService;

    @GetMapping("/retrieve")
    public ResponseEntity<?> retrieve(@RequestHeader("object-Id") String objectId){
        System.out.println("Received objectId = [" + objectId + "]");
        MessageEntity messageEntity = messageService.find(objectId);
        if(messageEntity != null){
            return ResponseEntity.ok(messageEntity.getMessage());
        }
        byte[] data = s3Service.retrieve(objectId);
        return ResponseEntity.ok(data);
    }
}

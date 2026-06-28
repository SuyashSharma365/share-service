package com.fileshare.fileshareapi.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/upload")
public class UploadController {

    @PostMapping
    public ResponseEntity<null> upload(){
        return ResponseEntity<null>;
    }

}

package com.fileshare.fileshareapi.service;

import com.fileshare.fileshareapi.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ShareService {

    private final S3Service s3Service;
    private final RedisTemplate<String , String> redisTemplate;
    private final IdGenerator idGenerator;

    public String upload(MultipartFile multipartFile){
        try{
            String id = idGenerator.generateId();
            String path  = s3Service.upload(multipartFile);
            redisTemplate.opsForValue().set(id , path , 12 , TimeUnit.HOURS);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}

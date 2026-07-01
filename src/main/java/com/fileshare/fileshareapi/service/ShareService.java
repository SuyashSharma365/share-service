package com.fileshare.fileshareapi.service;

import com.fileshare.fileshareapi.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class ShareService {

    private final S3Service s3Service;
//    private final RedisTemplate<String , String> redisTemplate;
    private final IdGenerator idGenerator;
    private final CacheService cache;

    public String upload(MultipartFile multipartFile){
        try{
            String id = idGenerator.generateId();
            String path  = s3Service.upload(multipartFile);
            cache.save(id , path);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void uploadForBoth(String id , MultipartFile multipartFile){
        try{
            String path = s3Service.upload(multipartFile);
            cache.save(id , path);
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

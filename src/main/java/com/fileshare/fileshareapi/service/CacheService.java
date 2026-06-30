package com.fileshare.fileshareapi.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CacheService {

    private final Map<String, String> fileMap = new ConcurrentHashMap<>();

    public void save(String id, String s3Key) {
        fileMap.put(id, s3Key);
    }

    public String find(String id) {
        return fileMap.get(id);
    }

    public void delete(String id) {
        fileMap.remove(id);
    }
}
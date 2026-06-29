package com.fileshare.fileshareapi.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class IdGenerator {

    private static final Random random = new Random();

    public String generateId(){
        return  String.valueOf(1000 + random.nextInt(9000));
    }
}

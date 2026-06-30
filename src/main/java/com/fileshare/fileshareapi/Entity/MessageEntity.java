package com.fileshare.fileshareapi.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class MessageEntity {

    @Id
    private String Id;

    private String message;
}

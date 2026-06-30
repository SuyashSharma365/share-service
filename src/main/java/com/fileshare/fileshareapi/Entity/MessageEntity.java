package com.fileshare.fileshareapi.Entity;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Data
@Builder
public class MessageEntity {

    @Id
    private String id;

    private String message;
}

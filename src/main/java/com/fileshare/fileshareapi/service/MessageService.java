package com.fileshare.fileshareapi.service;
import com.fileshare.fileshareapi.Entity.MessageEntity;
import com.fileshare.fileshareapi.Repository.MessageRepo;
import com.fileshare.fileshareapi.model.MessageDto;
import com.fileshare.fileshareapi.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final IdGenerator idGenerator;

    public String save(MessageDto messageDto) {
        String id = idGenerator.generateId();
        messageRepo.save(MessageEntity.builder().id(id).message(messageDto.getMessage()).build());
        return id;
    }

    public MessageEntity find(String Id){
        return messageRepo.findById(Id).orElse(null);
    }
}

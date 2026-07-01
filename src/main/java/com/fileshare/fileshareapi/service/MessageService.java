package com.fileshare.fileshareapi.service;
import com.fileshare.fileshareapi.Entity.MessageEntity;
import com.fileshare.fileshareapi.Repository.MessageRepo;
import com.fileshare.fileshareapi.model.RetrieveResponseDto;
import com.fileshare.fileshareapi.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepo messageRepo;
    private final IdGenerator idGenerator;
    private final ShareService shareService;

    public String save(RetrieveResponseDto messageDto) {
        String id = idGenerator.generateId();
        messageRepo.save(MessageEntity.builder().id(id).message(messageDto.getMessage()).build());
        return id;
    }

    public MessageEntity find(String Id){
        return messageRepo.findById(Id).orElse(null);
    }

    public String saveBoth(RetrieveResponseDto dto , MultipartFile multipartFile) throws IOException{
        String id = idGenerator.generateId();
        messageRepo.save(MessageEntity.builder().id(id).message(dto.getMessage()).build());
        shareService.uploadForBoth(id , multipartFile);
        return id;
    }
}

package com.fileshare.fileshareapi.Repository;

import com.fileshare.fileshareapi.Entity.MessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends MongoRepository<MessageEntity , String> {
}

package com.micr.userver.collections;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import com.micr.userver.documentobject.UserDO;

@Component
public interface UsersCollection extends MongoRepository<UserDO, String> {
    
}

package com.micr.userver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.micr.userver.model.UserDO;

@Component
public interface UsersCollection extends MongoRepository<UserDO, String> {
    UserDO findByEmail(String email);
}

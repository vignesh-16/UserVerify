package com.micr.userver.collections;

import com.micr.userver.documentobject.LogsDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface LogsCollection extends MongoRepository<LogsDO, String> {
    LogsDO findByUserId(String userId);
    LogsDO findByUserEmail(String userEmail);
}

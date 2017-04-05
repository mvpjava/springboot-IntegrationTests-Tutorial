package com.mvpjava.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<LogRecord, String>{
    
}

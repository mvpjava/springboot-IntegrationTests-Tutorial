package com.mvpjava.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class MongoService {

    private final MongoTemplate mongoTemplate;

    private final String logsCollectionName = "logs";

    @Autowired
    public MongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public LogRecord log(LogRecord logRecord) throws DataAccessException {
        mongoTemplate.save(logRecord, logsCollectionName);
        return findLogMessageById(logRecord.getId());
    }

    public LogRecord findLogMessageById(String id) {
        return mongoTemplate.findById(
                id, LogRecord.class, logsCollectionName);
    }

    public void removeLogCollection() {
        mongoTemplate.dropCollection(logsCollectionName);
    }

}

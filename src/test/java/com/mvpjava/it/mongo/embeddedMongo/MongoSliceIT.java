package com.mvpjava.it.mongo.embeddedMongo;

import static org.junit.Assert.*;
import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import com.mongodb.DBCollection;
import com.mvpjava.mongo.LogRecord;
import com.mvpjava.mongo.LogRepository;
import com.mvpjava.mongoconfig.SystemProfileValueSource2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ProfileValueSourceConfiguration(value = SystemProfileValueSource2.class)
@IfProfileValue(name = ACTIVE_PROFILES_PROPERTY_NAME, value = "it-embedded") //"spring.profiles.active"
@RunWith(SpringRunner.class)
@DataMongoTest
public class MongoSliceIT {

    String collectionName;
    LogRecord logRecToInsert;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LogRepository logRepository;

    @Before
    public void before() {
        collectionName = "logs";
        logRecToInsert = new LogRecord("INFO", "Test Info Log messge");
    }

    @After
    public void after() {
        mongoTemplate.dropCollection(collectionName);
    }

    @Test
    public void checkMongoTemplate() {
        assertNotNull(mongoTemplate);
        DBCollection createdCollection = mongoTemplate.createCollection(collectionName);
        assertTrue(mongoTemplate.collectionExists(collectionName));
    }

    @Test
    public void checkDocumentAndQuery() {
        mongoTemplate.save(logRecToInsert, collectionName);
        Query query = new Query(new Criteria()
                .andOperator(Criteria.where("level").regex(logRecToInsert.getLevel()),
                        Criteria.where("message").regex(logRecToInsert.getMessage())));

        LogRecord retrievedLogRecord = mongoTemplate.findOne(query, LogRecord.class, collectionName);
        assertNotNull(retrievedLogRecord);
    }

    @Test
    public void checkLogRepository() {
        assertNotNull(logRepository);
        LogRecord savedLogRecord = logRepository.save(logRecToInsert);
        assertNotNull(logRepository.findOne(savedLogRecord.getId()));
    }

}

package com.mvpjava.it.mongo.embeddedMongo;

import static org.junit.Assert.*;
import static org.springframework.boot.test.context.SpringBootTest.*;
import static org.springframework.core.env.AbstractEnvironment.*;

import com.mvpjava.mongo.LogRecord;
import com.mvpjava.mongoconfig.SystemProfileValueSource2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ProfileValueSourceConfiguration(value = SystemProfileValueSource2.class)
@IfProfileValue(name = ACTIVE_PROFILES_PROPERTY_NAME, value = "it-embedded")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration //mongoDB
public class MongoTomcatIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ensureLoggingWorks() {
        LogRecord logRecord = new LogRecord("INFO", "This is a test");
        ResponseEntity<LogRecord> responseEntity = restTemplate.postForEntity(
                "/log", logRecord, LogRecord.class);
        LogRecord logRecordReturned = responseEntity.getBody();

        assertNotNull("Should have an PK", logRecordReturned.getId());
    }
}

package com.mvpjava.it.mongo.embeddedMongo;

import static org.junit.Assert.*;
import static org.springframework.core.env.AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME;

import com.mvpjava.mongo.LogRecord;
import com.mvpjava.mongo.MongoService;
import com.mvpjava.mongoconfig.SystemProfileValueSource2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@ProfileValueSourceConfiguration(value = SystemProfileValueSource2.class)
@IfProfileValue(name = ACTIVE_PROFILES_PROPERTY_NAME, value = "it-embedded") //"spring.profiles.active"
@RunWith(SpringRunner.class)
@DataMongoTest(includeFilters = @Filter(Service.class))
public class MongoSliceWithServiceIT {

    @Autowired
    private MongoService mongoService;

    @Test
    public void ensureLoggingWorks() {
        LogRecord logRecord = new LogRecord("INFO", "This is a test");
        LogRecord logWithPK = mongoService.log(logRecord);
        assertNotNull(logWithPK.getId());
        assertEquals(logRecord.getMessage(), logWithPK.getMessage());
    }
}

package com.mvpjava.mongoconfig;

import java.util.Objects;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.ProfileValueSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component
public class SystemProfileValueSource2 implements ProfileValueSource {

    @Override
    public String get(String key) {
        Assert.hasText(key, "'key' must not be empty");
        String springProfiles = System.getProperty(key);
        if (Objects.isNull(key) || StringUtils.isEmpty(springProfiles)) {
            return "it-embedded";
        }

        return System.getProperty(key);
    }

}

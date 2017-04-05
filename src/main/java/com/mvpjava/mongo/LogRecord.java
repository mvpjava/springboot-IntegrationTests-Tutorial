package com.mvpjava.mongo;

import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Used in Mapping the Log record from Java Object to Mongo DBObject and back.
 */
@Document
public class LogRecord {
    @Id private String id;
    private String level;
    private Date timestamp;
    private String message;

    public LogRecord() {
    }

    public LogRecord(String level, String message) {
        this.level = level;
        this.message = message;
        setTimestamp(new Date());
    }
    

    public String getId() {
        return id;
    }

    public void setId(String mongoId) {
        this.id = mongoId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.level);
        hash = 23 * hash + Objects.hashCode(this.timestamp);
        hash = 23 * hash + Objects.hashCode(this.message);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LogRecord other = (LogRecord) obj;
        if (!Objects.equals(this.level, other.level)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.timestamp, other.timestamp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LogRecord{" + "id=" + id + ", level=" + level + ", timestamp=" + timestamp + ", message=" + message + '}';
    }

}

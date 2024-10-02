package com.micr.userver.documentobject;

import java.util.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "logs")
public class LogsDO {

    public static class Actions {

        private String action;

        private String status;

        private String reason;

        private long timedAt;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }

        public long getTimedAt() {
            return timedAt;
        }

        public void setTimedAt(long timedAt) {
            this.timedAt = timedAt;
        }

    }

    @Id
    private String id = new ObjectId().toHexString();
 
    @Field("userId")
    private String userId;

    @Field("userEmail")
    private String userEmail;

    @Field("logs")
    private ArrayList<Actions> actions;

    @Field("createdAt")
    private long createdAt;

    @Field ("lastModified")
    private long lastModified;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public ArrayList<Actions> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Actions> actions) {
        this.actions = actions;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LogsDO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", actions=" + actions +
                ", createdAt=" + createdAt +
                ", lastModified=" + lastModified +
                '}';
    }
}


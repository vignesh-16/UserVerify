package com.micr.userver.services;

import com.micr.userver.collections.LogsCollection;
import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.LogsDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LoggingServiceImpl implements  LoggingService{

    @Autowired
    LogsCollection logsDb;

    @Autowired
    UsersCollection userdb;

    @Override
    public void addSuccessfulLoginForUser(String userId) {

    };

    @Override
    public void addSuccessfulUserCreation(String userId, String email) {

    }

    @Override
    public void addUnsuccessfulLoginForUser(String userId, String email, String reason) {
        LogsDO userLogs = logsDb.findByUserId(userId);
        LogsDO.Actions action = new LogsDO.Actions();
        action.setAction("login");
        action.setStatus("Failure");
        action.setReason(reason);
        action.setTimedAt(System.currentTimeMillis());
        if (userLogs == null) {
            userLogs = new LogsDO();
            userLogs.setUserId(userId);
            userLogs.setUserEmail(email);
            ArrayList<LogsDO.Actions> actionsCollection = new ArrayList<>();
            actionsCollection.add(action);
            userLogs.setActions(actionsCollection);
            userLogs.setCreatedAt(System.currentTimeMillis());
            logsDb.insert(userLogs);
        } else {
            ArrayList<LogsDO.Actions> existingActions = userLogs.getActions();
            existingActions.add(action);
            userLogs.setLastModified(System.currentTimeMillis());
            userLogs.setActions(existingActions);
            logsDb.save(userLogs);
        }
    };

    @Override
    public void addUnknownUserLoginAttempts(String email) {
        LogsDO.Actions action = new LogsDO.Actions();
        action.setAction("login");
        action.setStatus("Failure");
        action.setReason("user email not found");
        action.setTimedAt(System.currentTimeMillis());
        LogsDO userLogs = logsDb.findByUserEmail(email);
        if (userLogs == null) {
            userLogs = new LogsDO();
            userLogs.setUserId("email_not_found");
            userLogs.setUserEmail(email);
            ArrayList<LogsDO.Actions> actionsCollection = new ArrayList<>();
            actionsCollection.add(action);
            userLogs.setActions(actionsCollection);
            userLogs.setCreatedAt(System.currentTimeMillis());
            logsDb.insert(userLogs);
        } else {
            ArrayList<LogsDO.Actions> existingActions = userLogs.getActions();
            existingActions.add(action);
            userLogs.setLastModified(System.currentTimeMillis());
            userLogs.setActions(existingActions);
            logsDb.save(userLogs);
        }
    };

}

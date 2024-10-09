package com.micr.userver.services;

public interface LoggingService {

    public void addSuccessfulLoginForUser(String userId);

    public void addUnsuccessfulLoginForUser(String userId, String email, String reason);

    public void addUnknownUserLoginAttempts(String email);

    public  void addSuccessfulUserCreation(String userId, String email);

}

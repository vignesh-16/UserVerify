package com.micr.userver.services;

import org.springframework.stereotype.Component;

import com.micr.userver.model.UserDO;

@Component
public interface UserService {

    public UserDO createNewUser(UserDO request);

}
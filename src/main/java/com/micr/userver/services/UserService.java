package com.micr.userver.services;

import com.micr.userver.documentobject.UserDO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    public UserDO createNewUser(UserDO request);

}
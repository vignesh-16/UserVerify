package com.micr.userver.services;

import com.micr.userver.documentobject.UserDO;

public interface UserService {

    public UserDO createNewUser(UserDO request);

}
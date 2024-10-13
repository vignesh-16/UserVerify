package com.micr.userver.services;

import com.micr.userver.documentobject.LoginParamsDO;
import com.micr.userver.documentobject.UserDO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public interface UserService {

    public UserDO createNewUser(UserDO request);

    public HashMap<String, String> authenticateLoginReq (LoginParamsDO request);

}
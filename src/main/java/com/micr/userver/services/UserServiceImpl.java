package com.micr.userver.services;

import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class UserServiceImpl implements UserService {

    public static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UsersCollection userDb;

    public UserDO createNewUser(UserDO request) {
        UserDO status = new UserDO();
        try {
            UserDO newUser = new UserDO (
                    request.getFirstname(),
                    request.getLastname(),
                    request.getFullname(),
                    request.getEmail(),
                    request.getPassword()
            );
            System.out.println("requested user: "+newUser);
            UserDO savedUser = userDb.save(newUser);
            System.out.println("Newly created user: "+savedUser);
            status = savedUser;
        } catch (Exception e) {
            log.error(e.getClass().getSimpleName());
            status = null;
        }
        return status;
    }

}

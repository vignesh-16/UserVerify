package com.micr.userver.services;

import com.micr.userver.model.UserDO;
import com.micr.userver.repository.UsersCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class UserServiceImpl implements UserService {

    public static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UsersCollection userDb;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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
            newUser.setPassword(encoder.encode(request.getPassword()));
            log.info("requested user: {}", newUser);
            UserDO savedUser = userDb.save(newUser);
            log.info("Newly created user: {}", savedUser);
            status = savedUser;
        } catch (Exception e) {
            log.error(e.getClass().getSimpleName());
            status = null;
        }
        return status;
    }
}

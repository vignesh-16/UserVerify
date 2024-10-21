package com.micr.userver.services;

import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.LoginParamsDO;
import com.micr.userver.documentobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class UserServiceImpl implements UserService {

    public static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    UsersCollection userDb;

    @Autowired
    LoggingServiceImpl logService;

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

    @Override
    public HashMap<String, String> authenticateLoginReq(LoginParamsDO request) {
        HashMap<String, String> responseMessage = new HashMap<>();
        responseMessage.put("RequestedAt", ""+System.currentTimeMillis());
        responseMessage.put("Message","Not processed");
        try {
            UserDO user = null;
            user = userDb.findByEmail(request.getEmail());
            log.info("User with matching email found!!!: {}", user);
            if (user == null) {
                responseMessage.put("Message", "User not found!");
                logService.addUnknownUserLoginAttempts(request.getEmail());
                return responseMessage;
            } else if (!user.getPassword().equals(request.getPassword())) {
                responseMessage.put("Message", "Password does not match!");
                //logService.addUnsuccessfulLoginForUser(user.getId(), user.getEmail(), responseMessage.get("Message"));
                return responseMessage;
            }
        } catch (Exception e) {
            log.error("{} {}", e.getClass().getSimpleName(), e.getMessage());
        }
        return responseMessage;
    }


}

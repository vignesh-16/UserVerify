package com.micr.userver.routes;

import com.micr.userver.collections.LogsCollection;
import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.LoginParamsDO;
import com.micr.userver.documentobject.UserDO;
import com.micr.userver.services.LoggingServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;


@RestController()
@RequestMapping("/auth")
public class Routes {

    private static final Logger log = LogManager.getLogger(Routes.class);
    @Autowired
    UsersCollection userDb;

    @Autowired
    LogsCollection logsDb;

    @Autowired
    LoggingServiceImpl logService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginParamsDO request) {
        try {
            UserDO user = null;
            user = userDb.findByEmail(request.getEmail());
            log.info("User with matching email found!!!: {}", user);
            HashMap<String, String> responseMessage = new HashMap<>();
            responseMessage.put("RequestedAt", ""+System.currentTimeMillis());
            if (user == null) {
                responseMessage.put("Message", "User not found!");
                logService.addUnknownUserLoginAttempts(request.getEmail());
                return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
            } else if (!user.getPassword().equals(request.getPassword())) {
                responseMessage.put("Message", "Password does not match!");
                logService.addUnsuccessfulLoginForUser(user.getId(), user.getEmail(), responseMessage.get("Message"));
                return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Trouble processing request:\n"+e);
        } finally {
            log.info("Request processed!");
        }
    }
    
    @GetMapping("/user")
    public String getMethodName() {
        return "name: user, email: user@gmail.com, accountType: member";
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createNewUser(@RequestBody UserDO req) {
        try {

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getClass().getSimpleName());
            if (e.getClass().getSimpleName().equals("DuplicateKeyException")) {
                HashMap<String, String> response = new HashMap<>();
                response.put("Error", "User email already registered!");
                return new ResponseEntity<>(response,HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}

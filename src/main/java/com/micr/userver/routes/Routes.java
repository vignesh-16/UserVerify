package com.micr.userver.routes;

import com.micr.userver.collections.LogsCollection;
import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.LoginParamsDO;
import com.micr.userver.documentobject.UserDO;
import com.micr.userver.services.LoggingServiceImpl;
import com.micr.userver.services.UserServiceImpl;
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
    UserServiceImpl userService;

    @Autowired
    LoggingServiceImpl logService;

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginParamsDO request) {
        try {


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
    public ResponseEntity<?> createNewUser(@RequestBody UserDO req) throws RuntimeException {
        try {
            HashMap<String, UserDO> status = new HashMap<>();
            UserDO response = userService.createNewUser(req);
            if (response != null) {
                status.put("newUser", response);
            } else {
                throw new RuntimeException("Could not create new user");
            }
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{} {}", e.getClass().getSimpleName(), e.getMessage());
            HashMap<String, String> response = new HashMap<>();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

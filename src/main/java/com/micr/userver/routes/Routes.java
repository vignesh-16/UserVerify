package com.micr.userver.routes;

import com.micr.userver.model.LoginParamsDo;
import com.micr.userver.model.UserDO;
import com.micr.userver.repository.UsersCollection;
import com.micr.userver.services.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.rmi.ServerException;
import java.util.HashMap;



@RestController()
@RequestMapping("/auth")
public class Routes {

    private static final Logger log = LogManager.getLogger(Routes.class);
    @Autowired
    UsersCollection userDb;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/user")
    public String getMethodName() {
        return "name: user, email: user@gmail.com, accountType: member";
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody LoginParamsDo request) {
        return userService.verifyUser(request);
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createNewUser(@RequestBody UserDO req) throws ServerException {
        try {
            HashMap<String, UserDO> status = new HashMap<>();
            UserDO response = userService.createNewUser(req);
            if (response != null) {
                status.put("newUser", response);
            } else {
                throw new ServerException("Could not create new user");
            }
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            log.error("{} {}", e.getClass().getSimpleName(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCSRF_Token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

}

package com.micr.userver.routes;

import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.LoginParamsDO;
import com.micr.userver.documentobject.UserDO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Objects;


@RestController()
@RequestMapping("/auth")
public class Routes {

    @Autowired
    UsersCollection userDb;

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginParamsDO request) {
        try {
            UserDO user = null;
            user = userDb.findByEmail(request.getEmail());
            System.out.println("User with matching email found!!!"+user);
            if (user == null) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("RequestedAt", ""+System.currentTimeMillis());
                ErrorMessage.put("Message", "User not found!");
                return new ResponseEntity<>(ErrorMessage, HttpStatus.NOT_FOUND);
            } else if (! user.getPassword().equals(request.getPassword())) {
                HashMap<String, String> ErrorMessage = new HashMap<>();
                ErrorMessage.put("RequestedAt", ""+System.currentTimeMillis());
                ErrorMessage.put("Message", "Authentication key provided does not match!");
                return new ResponseEntity<>(ErrorMessage, HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Trouble processing request:\n"+e);
        } finally {
            System.out.println("Request processed!");
        }
    }
    
    @GetMapping("/user")
    public String getMethodName() {
        return "name: user, email: user@gmail.com, accountType: member";
    }

    @PostMapping("/createuser")
    public UserDO postMethodName(@RequestBody UserDO req) {
        UserDO newUser = new UserDO (
                req.getFirstname(),
                req.getLastname(),
                req.getFullname(),
                req.getEmail(),
                req.getPassword()
        );
        System.out.println("Requested user: "+newUser);
        UserDO savedUser = userDb.save(newUser);
        System.out.println("Newly created user: "+savedUser);
        return savedUser;
    }
    
    
}

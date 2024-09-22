package com.micr.userver.routes;

import com.micr.userver.collections.UsersCollection;
import com.micr.userver.documentobject.UserDO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;



@RestController()
@RequestMapping("/auth")
public class Routes {

    @Autowired
    UsersCollection userDb;

    @PostMapping("/login")
    public String postMethodName(@RequestBody String entity) {
        System.out.println("Route found!!!"+entity);
        return entity;
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

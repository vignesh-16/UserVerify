package com.micr.userver.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController()
@RequestMapping("/auth")
public class Routes {

    @PostMapping("/login")
    public String postMethodName(@RequestBody String entity) {
        System.out.println("Route found!!!");
        return entity;
    }
    
    @GetMapping("/user")
    public String getMethodName() {
        return "name: user, email: user@gmail.com, accountType: member";
    }
    
}

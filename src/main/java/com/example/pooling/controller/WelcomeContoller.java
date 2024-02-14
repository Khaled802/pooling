package com.example.pooling.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class WelcomeContoller {
    @GetMapping("/welcome")
    @Secured("USER")
    public String getMethodName() {
        return "welcome";
    }

    @GetMapping("/welcome-admin")
    @Secured("ADMIN")
    public String welcomeAdmin() {
        return "welcome";
    }
    
}

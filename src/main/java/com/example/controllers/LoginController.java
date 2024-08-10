package com.example.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/api/login")
    public String home(@RequestParam String username, @RequestParam String email, @RequestParam String password) {

        System.out.println("Received username: " + username + ", password: " + password + ", email: " + email);
        return "Received username: " + username + ", password: " + password + ", email: " + email;
    }
}

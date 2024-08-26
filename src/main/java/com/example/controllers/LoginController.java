package com.example.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.User;
import com.example.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/api/login")
    public ResponseEntity<ObjectNode> login(@RequestParam String username, @RequestParam String email, @RequestParam String password) {
        try {
            ObjectNode responseJson = mapper.createObjectNode();
            System.out.println("Received username: " + username + ", password: " + password + ", email: " + email);

            Optional<User> findUser = userService.findUser(username, password, email);
        
            if(findUser.isPresent()) {
                System.out.println("Такой пользователь уже есть");
                logger.info("User authorized: {}", username);
                responseJson.put("authorized", true);
                responseJson.put("id", findUser.get().getId());
                return ResponseEntity.ok(responseJson);
            } else {
                User user = new User(username, password, email);
                userService.saveUser(user);
                logger.info("Create new user: {}, {}, {}", username, password, email);
                responseJson.put("authorized", false);
                responseJson.put("id", user.getId());
                return ResponseEntity.ok(responseJson);
            }
        } catch (RuntimeException e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/api/home")
    public String home() {
        return "Hello world";
    }
}
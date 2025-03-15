package com.hotelbooker.controller;

import com.hotelbooker.model.User;
import com.hotelbooker.security.JwtUtil;
import com.hotelbooker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        Optional<User> existingUser = userService.findByUsername(user.getUsername());

        if (existingUser.isPresent() && existingUser.get().getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}

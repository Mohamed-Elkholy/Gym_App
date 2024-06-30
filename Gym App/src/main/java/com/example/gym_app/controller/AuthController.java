package com.example.gym_app.controller;

import com.example.gym_app.dto.LoginRequest;
import com.example.gym_app.dto.RegisterRequest;
import com.example.gym_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.authenticateUser(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser() {
        return ResponseEntity.ok(service.logoutUser());
    }
}

package com.example.gym_app.service;

import com.example.gym_app.dto.LoginRequest;
import com.example.gym_app.dto.RegisterRequest;
import com.example.gym_app.model.User;
import com.example.gym_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public String registerUser(RegisterRequest request) {
        if(userRepository.findByUsername(request.getUsername()) != null) {
            return "Username is already taken";
        }
        if(!request.getPassword().equals(request.getConfirmPassword())) {
            return "Password and Confirm Password do not match";
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
        return "Registration successful. Welcome!";
    }

    public String authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login successful. Welcome back!";
    }

    public String logoutUser() {
        SecurityContextHolder.clearContext();
        return "Logout successful. See you again soon!";
    }
}

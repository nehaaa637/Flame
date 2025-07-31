package com.bankingsim.controller;

import com.bankingsim.model.Account;
import com.bankingsim.model.User;
import com.bankingsim.repository.AccountRepository;
import com.bankingsim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already exists.";
        }
        userRepository.save(user);
        return "User registered successfully.";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful.";
        }
        return "Invalid username or password.";
    }
}
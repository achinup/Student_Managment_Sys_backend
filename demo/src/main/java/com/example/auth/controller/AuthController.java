package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to call backend
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Signup API
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // Login API
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}

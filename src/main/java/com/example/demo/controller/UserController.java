package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {

            return Map.of(
                    "message",
                    "Email already exists"
            );
        }

        user.setId(null);

        userRepository.save(user);

        return Map.of(
                "message",
                "User Registered Successfully"
        );
    }

    @PostMapping("/login")
    public Map<String, String> loginUser(@RequestBody Map<String, String> body) {


        String email = body.get("email");
        String password = body.get("password");

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {

            return Map.of(
                    "message",
                    "User not found"
            );
        }

        if (!user.getPassword().equals(password)) {

            return Map.of(
                    "message",
                    "Invalid Password"
            );
        }

        String token = jwtUtil.generateToken(email);

        return Map.of(
                "message", "Login Success",
                "token", token
        );
    }
}
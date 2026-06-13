package com.task.taksmanager.controller;

import com.task.taksmanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.task.taksmanager.model.User;
import com.task.taksmanager.repository.UserRepository;
import java.util.List;
import java.util.Map;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) {
        return authService.register(
                request.get("username"),
                request.get("password")
        );
    }
    @PutMapping("/update-password")
    public String updatePassword(@RequestBody Map<String, String> request) {
        return authService.updatePassword(
                request.get("username"),
                request.get("newPassword")
        );
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        return authService.login(
                request.get("username"),
                request.get("password")
        );


    }
        @PostMapping("/reset-password")
        public String resetPassword(@RequestBody Map<String, String> request) {
            return authService.resetPassword(
                    request.get("username"),
                    request.get("newPassword")
            );
        }
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
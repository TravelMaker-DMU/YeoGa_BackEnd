package com.example.yeoga.controller;

import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/quit")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String username = authentication.getName();
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
        UserEntity updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
    @PostMapping("/find-username")
    public ResponseEntity<?> findUsername(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String username = userService.findUsernameByEmail(email);
        return ResponseEntity.ok("Your username is: " + username);
    }
}

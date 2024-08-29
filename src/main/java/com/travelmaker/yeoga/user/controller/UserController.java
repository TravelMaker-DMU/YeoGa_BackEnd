package com.travelmaker.yeoga.user.controller;

import com.travelmaker.yeoga.user.dto.SignupDTO;
import com.travelmaker.yeoga.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO) {
        return userService.registerUser(signupDTO);
    }
}
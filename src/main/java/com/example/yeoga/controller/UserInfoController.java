package com.example.yeoga.controller;

import com.example.yeoga.dto.UserDTO;
import com.example.yeoga.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
public class UserInfoController {


    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public UserDTO getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
package com.example.yeoga.controller;

import com.example.yeoga.dto.UserDTO;
import com.example.yeoga.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/my")
public class UserInfoController {


    private final UserService userService;

    public UserInfoController(UserService userService) {
        this.userService = userService;
    }

    @Operation(description = "마이페이지를 조회하기 위한 API, 유저 번호대로 조회 가능하다.", summary = "마이페이지")
    @GetMapping("/{username}")
    public UserDTO getUser(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}
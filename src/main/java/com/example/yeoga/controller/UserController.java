package com.example.yeoga.controller;

import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "회원 탈퇴 가능 API",summary = "회원 탈퇴")
    @DeleteMapping("/quit")
    public ResponseEntity<String> deleteUser(Authentication authentication) {
        String username = authentication.getName();
        userService.deleteUserByUsername(username);
        return ResponseEntity.ok("User deleted successfully");
    }
    @Operation(description = "회원 정보 업데이트 API", summary = "회원 정보 수정")
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
        UserEntity updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
    @Operation(description = "회원 아이디 조회하기 위한 API", summary = "회원 아이디 찾기")
    @PostMapping("/find-username")
    public ResponseEntity<?> findUsername(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String username = userService.findUsernameByEmail(email);
        return ResponseEntity.ok("Your username is: " + username);
    }
}

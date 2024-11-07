package com.example.yeoga.controller;

import com.example.yeoga.dto.RecentViewDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.RecentViewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my/{username}")
public class RecentViewController {

    private final RecentViewService recentViewService;

    public RecentViewController(RecentViewService recentViewService) {
        this.recentViewService = recentViewService;
    }


    @Operation(description = "최근 본 코스 추가 API", summary = "최근 본 코스 추가")
    @PostMapping("/recent")
    public ResponseEntity<?> addRecentView(@RequestParam String courseId, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        recentViewService.addRecentView(user, courseId);
        return ResponseEntity.ok("Recent view added successfully.");
    }

    @Operation(description = "최근 추가한 코스를 조회하는 API", summary = "최근 추가한 코스 조회")
    @GetMapping("/recent")
    public ResponseEntity<List<RecentViewDTO>> getRecentViews(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<RecentViewDTO> recentViews = recentViewService.getRecentViews(user);
        return ResponseEntity.ok(recentViews);
    }
}
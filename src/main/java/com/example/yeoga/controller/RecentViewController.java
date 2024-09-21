package com.example.yeoga.controller;

import com.example.yeoga.dto.RecentViewDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.RecentViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recent")
public class RecentViewController {

    private final RecentViewService recentViewService;

    public RecentViewController(RecentViewService recentViewService) {
        this.recentViewService = recentViewService;
    }

    // 최근 본 코스 추가
    @PostMapping
    public ResponseEntity<?> addRecentView(@RequestParam String courseId, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        recentViewService.addRecentView(user, courseId);
        return ResponseEntity.ok("Recent view added successfully.");
    }

    // 최근 본 코스 목록 조회
    @GetMapping
    public ResponseEntity<List<RecentViewDTO>> getRecentViews(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<RecentViewDTO> recentViews = recentViewService.getRecentViews(user);
        return ResponseEntity.ok(recentViews);
    }
}
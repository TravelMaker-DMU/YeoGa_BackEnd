package com.travelmaker.yeoga.controller;

import com.travelmaker.yeoga.dto.RecentViewDTO;
import com.travelmaker.yeoga.model.Attraction;
import com.travelmaker.yeoga.model.RecentView;
import com.travelmaker.yeoga.repository.AccommodationRepository;
import com.travelmaker.yeoga.repository.AttractionRepository;
import com.travelmaker.yeoga.repository.PostRepository;
import com.travelmaker.yeoga.service.RecentViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.travelmaker.yeoga.model.Page;
import com.travelmaker.yeoga.dto.SaveRecentViewRequest;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/recentviews")
public class RecentViewController {

    private final RecentViewService recentViewService;
    private final AccommodationRepository accommodationRepository;
    private final AttractionRepository attractionRepository;
    private final PostRepository postRepository;

    @Autowired
    public RecentViewController(RecentViewService recentViewService,
                                AccommodationRepository accommodationRepository,
                                AttractionRepository attractionRepository,
                                PostRepository postRepository) {
        this.recentViewService = recentViewService;
        this.accommodationRepository = accommodationRepository;
        this.attractionRepository = attractionRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getRecentViews(@PathVariable Long userId, @AuthenticationPrincipal UserDetails userDetails) {

        //로그인 여부 체크
        if (userDetails == null || !userDetails.getUsername().equals(String.valueOf(userId))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요한 기능입니다.");
        }

        //최근 방문한 페이지 가져오기
        List<RecentView> recentViews = recentViewService.getRecentViewsByUser(userId);
        if (recentViews.isEmpty()) {
            return ResponseEntity.ok("최근 방문한 페이지가 없습니다.");
        }

        List<RecentViewDTO> response = recentViews.stream()
                .map(RecentViewDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> saveRecentView(@PathVariable Long userId, @RequestBody SaveRecentViewRequest request)
    {
        Page page = findPageById(request.getPageId(), request.getPageType());
        recentViewService.saveRecentView(userId, page);
        return ResponseEntity.ok("최근 본 페이지가 저장되었습니다.");
    }

    private Page findPageById(Long pageId, String pageType)
    {
        switch (pageType) {
            case "Accommodation":
                return accommodationRepository.findById(pageId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 숙소 페이지를 찾을 수 없습니다. id=" + pageId));
            case "Attraction":
                return attractionRepository.findById(pageId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 명소를 찾을 수 없습니다. id=" + pageId));
            case "Post":
                return postRepository.findById(pageId)
                        .orElseThrow(() -> new IllegalArgumentException("해당 페이지를 찾을 수 없습니다. id=" + pageId));
            default:
                throw new IllegalArgumentException("유효하지 않은 페이지 타입입니다." + pageType);
        }
    }
}

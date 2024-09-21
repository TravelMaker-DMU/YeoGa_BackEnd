package com.example.yeoga.controller;

import com.example.yeoga.dto.NewsDTO;
import com.example.yeoga.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    // 뉴스 목록 조회
    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAllNews() {
        List<NewsDTO> newsList = newsService.getAllNews();
        return ResponseEntity.ok(newsList);
    }

    // 뉴스 상세 조회
    @GetMapping("/{newsId}")
    public ResponseEntity<?> getNewsById(@PathVariable int newsId) {
        Optional<NewsDTO> news = newsService.getNewsById(newsId);
        if (news.isPresent()) {
            return ResponseEntity.ok(news.get());
        } else {
            return ResponseEntity.status(404).body("News not found.");
        }
    }

    // 뉴스 생성 (관리자 기능)
    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody NewsDTO newsDTO) {
        NewsDTO createdNews = newsService.createNews(newsDTO);
        return ResponseEntity.ok(createdNews);
    }

    // 뉴스 삭제 (관리자 기능)
    @DeleteMapping("/{newsId}")
    public ResponseEntity<?> deleteNews(@PathVariable int newsId) {
        newsService.deleteNews(newsId);
        return ResponseEntity.ok("News deleted successfully.");
    }
}
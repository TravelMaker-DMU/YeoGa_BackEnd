
package com.example.yeoga.controller;

import com.example.yeoga.dto.BookmarkDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.BookmarkService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/my/{username}")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    // 북마크 추가
    @PostMapping("/bookmarks")
    public ResponseEntity<?> addBookmark(@RequestParam String courseId, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        bookmarkService.addBookmark(user, courseId);
        return ResponseEntity.ok("Bookmark added successfully.");
    }

    // 북마크 삭제
    @DeleteMapping("/bookmarks")
    public ResponseEntity<?> removeBookmark(@RequestParam String courseId, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        bookmarkService.removeBookmark(user, courseId);
        return ResponseEntity.ok("Bookmark removed successfully.");
    }

    // 북마크 목록 조회
    @GetMapping("/bookmarks")
    public ResponseEntity<List<BookmarkDTO>> getBookmarks(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<BookmarkDTO> bookmarks = bookmarkService.getBookmarks(user);
        return ResponseEntity.ok(bookmarks);
    }
}
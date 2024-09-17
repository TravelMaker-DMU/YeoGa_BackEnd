package com.travelmaker.yeoga.controller;

import com.travelmaker.yeoga.dto.BookmarkDTO;
import com.travelmaker.yeoga.model.Bookmark;
import com.travelmaker.yeoga.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    @Autowired
    private BookmarkService bookmarkService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<BookmarkDTO>> getUserBookmarks(@PathVariable Long userId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUser(userId);
        List<BookmarkDTO> response = bookmarks.stream()
                .map(BookmarkDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<BookmarkDTO> addBookmark(
            @PathVariable Long userId,
            @RequestParam String BookmarkId,
            @RequestParam String BookmarkTitle,
            @RequestParam LocalDateTime BMCDate
            ) {
        Bookmark bookmark = bookmarkService.addBookmark(userId, BookmarkId, BookmarkTitle, BMCDate);
        return ResponseEntity.ok(BookmarkDTO.fromEntity(bookmark));
    }

    @DeleteMapping("/{userId}/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long userId, @PathVariable Long bookmarkId) {
        bookmarkService.removeBookmark(userId, bookmarkId);
        return ResponseEntity.noContent().build();
    }
}

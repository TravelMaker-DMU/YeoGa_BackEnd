package com.example.yeoga.service;

import com.example.yeoga.dto.BookmarkDTO;
import com.example.yeoga.entity.BookmarkEntity;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.repository.BookmarkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public void addBookmark(UserEntity user, String courseId) {
        if (!bookmarkRepository.existsByUserAndCourseId(user, courseId)) {
            BookmarkEntity bookmark = new BookmarkEntity();
            bookmark.setUser(user);
            bookmark.setCourseId(courseId);
            bookmarkRepository.save(bookmark);
        }
    }

    public void removeBookmark(UserEntity user, String courseId) {
        bookmarkRepository.deleteByUserAndCourseId(user, courseId);
    }

    public List<BookmarkDTO> getBookmarks(UserEntity user) {
        List<BookmarkEntity> bookmarks = bookmarkRepository.findByUser(user);
        return bookmarks.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookmarkDTO convertToDTO(BookmarkEntity entity) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setBookmarkId(entity.getBookmarkId());
        dto.setCourseId(entity.getCourseId());
        dto.setDateAdded(entity.getDateAdded());
        return dto;
    }
}
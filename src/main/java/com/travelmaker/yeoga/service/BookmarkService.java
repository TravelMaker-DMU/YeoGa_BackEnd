package com.travelmaker.yeoga.service;

import com.travelmaker.yeoga.Exception.ResourceNotFoundException;
import com.travelmaker.yeoga.model.Bookmark;
import com.travelmaker.yeoga.model.User;
import com.travelmaker.yeoga.repository.UserRepository;
import com.travelmaker.yeoga.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserService userService;

    public List<Bookmark> getBookmarksByUser(Long userId) {
        Optional<User> user = userService.findById(userId);
        return bookmarkRepository.findByUser(user);
    }

    public Bookmark addBookmark(Long userId, String BookmarkId, String BookmarkTitle, LocalDateTime BMCDate) {
        Optional<User> user = userService.findById(userId);
        Bookmark bookmark = new Bookmark();
        bookmark.setUser(user);
        bookmark.setBookmarkId(BookmarkId);
        bookmark.setBookmarkTitle(BookmarkTitle);
        bookmark.setBMCDate(BMCDate);
        return bookmarkRepository.save(bookmark);
    }

    public void removeBookmark(Long userId, Long bookmarkId) {
        Optional<User> user = userService.findById(userId);
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId)
                .orElseThrow(() -> new ResourceNotFoundException("Bookmark not found"));
        if (bookmark.getUser().equals(user)) {
            bookmarkRepository.delete(bookmark);
        }
    }
}

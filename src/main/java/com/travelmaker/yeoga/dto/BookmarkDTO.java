package com.travelmaker.yeoga.dto;

import com.travelmaker.yeoga.model.Bookmark;

import java.time.LocalDateTime;

public class BookmarkDTO {
    private Long id;
    private String BookmarkId;
    private String BookmarkTitle;
    private LocalDateTime BMCDate;

    public static BookmarkDTO fromEntity(Bookmark bookmark) {
        BookmarkDTO dto = new BookmarkDTO();
        dto.setId(bookmark.getId());
        dto.setBookmarkId(bookmark.getBookmarkId());
        dto.setBookmarkTitle(bookmark.getBookmarkTitle());
        dto.setBMCDate(bookmark.getBMCDate());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookmarkId() {
        return BookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        BookmarkId = bookmarkId;
    }

    public String getBookmarkTitle() {
        return BookmarkTitle;
    }

    public void setBookmarkTitle(String bookmarkTitle) {
        BookmarkTitle = bookmarkTitle;
    }

    public LocalDateTime getBMCDate() {
        return BMCDate;
    }

    public void setBMCDate(LocalDateTime BMCDate) {
        this.BMCDate = BMCDate;
    }
}

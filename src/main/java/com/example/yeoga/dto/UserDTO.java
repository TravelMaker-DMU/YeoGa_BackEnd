package com.example.yeoga.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String role;
    private List<RecentViewDTO> recentViews;
    private List<BookmarkDTO> bookmarks;
    private List<CalendarEventDTO> calendarEvents;
}
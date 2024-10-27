package com.example.yeoga.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String username;
    private String role;
    private String email;
    private String birthday;
    private String tel;

    private List<RecentViewDTO> recentViews;
    private List<BookmarkDTO> bookmarks;
    private List<CalendarEventDTO> calendarEvents;
}
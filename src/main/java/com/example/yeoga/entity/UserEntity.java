package com.example.yeoga.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RecentViewEntity> recentViews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BookmarkEntity> bookmarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CalendarEventEntity> calendarEvents;
}

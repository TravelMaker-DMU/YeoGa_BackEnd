package com.example.yeoga.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookmarkDTO {

    private int bookmarkId;
    private String courseId;
    private LocalDateTime dateAdded;
}
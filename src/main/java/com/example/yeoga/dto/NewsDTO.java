package com.example.yeoga.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NewsDTO {

    private int newsId;
    private String title;
    private String summary;
    private String content;
    private String imageUrl;
    private LocalDateTime date;
}
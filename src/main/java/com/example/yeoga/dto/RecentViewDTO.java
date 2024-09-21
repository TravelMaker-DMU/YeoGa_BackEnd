package com.example.yeoga.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecentViewDTO {

    private int viewId;
    private String courseId;
    private LocalDateTime viewedAt;
}
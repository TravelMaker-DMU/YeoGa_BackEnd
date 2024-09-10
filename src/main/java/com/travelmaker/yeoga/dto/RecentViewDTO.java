package com.travelmaker.yeoga.dto;

import com.travelmaker.yeoga.model.RecentView;

import java.time.LocalDateTime;

public class RecentViewDTO {
    private Long id;
    private String pageTitle;
    private String pageType;
    private LocalDateTime viewedAt;

    public static RecentViewDTO fromEntity(RecentView recentView) {
        RecentViewDTO dto = new RecentViewDTO();
        dto.setId(recentView.getId());
        dto.setPageTitle(recentView.getPage().getTitle());
        dto.setPageType(recentView.getPage().getClass().getSimpleName());
        dto.setViewedAt(recentView.getViewedAt());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public LocalDateTime getViewedAt() {
        return viewedAt;
    }

    public void setViewedAt(LocalDateTime viewedAt) {
        this.viewedAt = viewedAt;
    }
}

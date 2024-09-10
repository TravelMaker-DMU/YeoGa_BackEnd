package com.travelmaker.yeoga.dto;

public class SaveRecentViewRequest {
    private Long pageId;
    private String pageType;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }
}

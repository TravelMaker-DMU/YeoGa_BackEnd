package com.example.yeoga.service;

import com.example.yeoga.dto.RecentViewDTO;
import com.example.yeoga.entity.RecentViewEntity;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.repository.RecentViewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecentViewService {

    private final RecentViewRepository recentViewRepository;

    public RecentViewService(RecentViewRepository recentViewRepository) {
        this.recentViewRepository = recentViewRepository;
    }

    public void addRecentView(UserEntity user, String courseId) {
        RecentViewEntity recentView = new RecentViewEntity();
        recentView.setUser(user);
        recentView.setCourseId(courseId);
        recentViewRepository.save(recentView);
    }

    public List<RecentViewDTO> getRecentViews(UserEntity user) {
        List<RecentViewEntity> recentViews = recentViewRepository.findByUser(user);
        return recentViews.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private RecentViewDTO convertToDTO(RecentViewEntity entity) {
        RecentViewDTO dto = new RecentViewDTO();
        dto.setViewId(entity.getViewId());
        dto.setCourseId(entity.getCourseId());
        dto.setViewedAt(entity.getViewedAt());
        return dto;
    }
}
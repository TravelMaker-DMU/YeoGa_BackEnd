package com.travelmaker.yeoga.service;
import com.travelmaker.yeoga.model.Page;
import com.travelmaker.yeoga.model.RecentView;
import com.travelmaker.yeoga.model.User;
import com.travelmaker.yeoga.repository.UserRepository;
import com.travelmaker.yeoga.repository.RecentViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecentViewService {

    private final RecentViewRepository recentViewRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecentViewService(RecentViewRepository recentViewRepository, UserRepository userRepository)
    {
        this.recentViewRepository = recentViewRepository;
        this.userRepository = userRepository;
    }

    public void saveRecentView(Long userId, Page page) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));

        RecentView recentView = new RecentView();
        recentView.setUser(user);
        recentView.setPage(page);  // 구체적인 Page 엔티티를 전달
        recentView.setViewedAt(LocalDateTime.now());

        recentViewRepository.save(recentView);
    }

    public List<RecentView> getRecentViewsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));

        return recentViewRepository.findByUserOrderByViewedAtDesc(user);  // 최신순으로 정렬
    }
}

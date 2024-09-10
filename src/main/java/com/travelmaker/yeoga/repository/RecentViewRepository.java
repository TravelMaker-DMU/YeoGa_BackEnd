package com.travelmaker.yeoga.repository;
import com.travelmaker.yeoga.model.RecentView;
import com.travelmaker.yeoga.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecentViewRepository extends JpaRepository<RecentView, Long> {
    List<RecentView> findByUserOrderByViewedAtDesc(User user);
}
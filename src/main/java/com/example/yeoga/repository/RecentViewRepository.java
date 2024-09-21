package com.example.yeoga.repository;

import com.example.yeoga.entity.RecentViewEntity;
import com.example.yeoga.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecentViewRepository extends JpaRepository<RecentViewEntity, Integer> {
    List<RecentViewEntity> findByUser(UserEntity user);
}
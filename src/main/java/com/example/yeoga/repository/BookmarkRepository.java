package com.example.yeoga.repository;


import com.example.yeoga.entity.BookmarkEntity;
import com.example.yeoga.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Integer> {
    List<BookmarkEntity> findByUser(UserEntity user);
    boolean existsByUserAndCourseId(UserEntity user, String courseId);
    void deleteByUserAndCourseId(UserEntity user, String courseId);
}
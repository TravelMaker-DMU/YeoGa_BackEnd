
package com.example.yeoga.repository;

import com.example.yeoga.entity.CalendarEventEntity;
import com.example.yeoga.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarEventRepository extends JpaRepository<CalendarEventEntity, Integer> {
    List<CalendarEventEntity> findByUser(UserEntity user);
}
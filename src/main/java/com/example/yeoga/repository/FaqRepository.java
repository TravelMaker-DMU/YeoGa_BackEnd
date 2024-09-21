package com.example.yeoga.repository;

import com.example.yeoga.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {
}
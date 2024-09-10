package com.travelmaker.yeoga.repository;

import com.travelmaker.yeoga.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
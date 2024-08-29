package com.travelmaker.yeoga.post.repository;

import com.travelmaker.yeoga.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
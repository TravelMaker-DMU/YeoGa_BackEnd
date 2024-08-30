package com.travelmaker.yeoga.service;

import com.travelmaker.yeoga.model.Post;
import com.travelmaker.yeoga.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // 게시물 목록 보기 (페이지네이션 포함)
    public List<Post> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable).getContent();
    }

    // 게시물 상세보기
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    // 게시물 작성하기
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setReadOnly(true);  // 게시물은 기본적으로 읽기 전용으로 설정
        return postRepository.save(post);
    }
}
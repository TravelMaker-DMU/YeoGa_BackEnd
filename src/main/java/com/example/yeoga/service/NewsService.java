
package com.example.yeoga.service;

import com.example.yeoga.dto.NewsDTO;
import com.example.yeoga.entity.NewsEntity;
import com.example.yeoga.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<NewsDTO> getAllNews() {
        List<NewsEntity> newsList = newsRepository.findAll();
        return newsList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<NewsDTO> getNewsById(int newsId) {
        Optional<NewsEntity> news = newsRepository.findById(newsId);
        return news.map(this::convertToDTO);
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        NewsEntity news = convertToEntity(newsDTO);
        NewsEntity savedNews = newsRepository.save(news);
        return convertToDTO(savedNews);
    }

    public void deleteNews(int newsId) {
        newsRepository.deleteById(newsId);
    }

    private NewsDTO convertToDTO(NewsEntity entity) {
        NewsDTO dto = new NewsDTO();
        dto.setNewsId(entity.getNewsId());
        dto.setTitle(entity.getTitle());
        dto.setSummary(entity.getSummary());
        dto.setContent(entity.getContent());
        dto.setImageUrl(entity.getImageUrl());
        dto.setDate(entity.getDate());
        return dto;
    }

    private NewsEntity convertToEntity(NewsDTO dto) {
        NewsEntity entity = new NewsEntity();
        entity.setTitle(dto.getTitle());
        entity.setSummary(dto.getSummary());
        entity.setContent(dto.getContent());
        entity.setImageUrl(dto.getImageUrl());
        return entity;
    }
}
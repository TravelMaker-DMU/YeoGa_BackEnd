
package com.example.yeoga.repository;

import com.example.yeoga.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {

}
package com.travelmaker.yeoga.repository;

import com.travelmaker.yeoga.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.travelmaker.yeoga.model.Bookmark;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser(Optional<User> user);
}

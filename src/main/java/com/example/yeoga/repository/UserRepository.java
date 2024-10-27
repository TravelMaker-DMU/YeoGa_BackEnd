package com.example.yeoga.repository;

import com.example.yeoga.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    void deleteByUsername(String username);

    Optional<UserEntity> findByEmail(String email);
}

package com.travelmaker.yeoga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.travelmaker.yeoga.model.ACCOUNT;

import java.util.Optional;

public interface ACCOUNTRepository extends JpaRepository<ACCOUNT, Long> {
    Optional<ACCOUNT> findByUuid(String uuid);
}

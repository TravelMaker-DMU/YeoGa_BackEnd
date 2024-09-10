package com.travelmaker.yeoga.repository;

import com.travelmaker.yeoga.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
}
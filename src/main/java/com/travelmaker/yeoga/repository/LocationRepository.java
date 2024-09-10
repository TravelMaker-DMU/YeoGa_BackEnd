package com.travelmaker.yeoga.repository;

import com.travelmaker.yeoga.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
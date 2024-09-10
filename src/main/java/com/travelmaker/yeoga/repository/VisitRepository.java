package com.travelmaker.yeoga.repository;

import com.travelmaker.yeoga.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    @Query("SELECT v.accommodation.id, COUNT(v.id) as visitCount FROM Visit v GROUP BY v.accommodation.id ORDER BY visitCount DESC")
    List<Object[]> findTopVisitedAccommodations(Pageable pageable);
}
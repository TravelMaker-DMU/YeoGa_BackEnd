package com.travelmaker.yeoga.service;

import com.travelmaker.yeoga.model.Accommodation;
import com.travelmaker.yeoga.repository.VisitRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccommodationService {

    private final VisitRepository visitRepository;


    public AccommodationService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }
    //
    public List<Accommodation> getTopVisitedAccommodations(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Object[]> results = visitRepository.findTopVisitedAccommodations(pageable);

        List<Accommodation> accommodations = new ArrayList<>();
        for (Object[] result : results) {
            Long accommodationId = (Long) result[0];
            // Accommodation을 데이터베이스에서 조회해서 리스트에 추가
        }

        return accommodations;
    }
}
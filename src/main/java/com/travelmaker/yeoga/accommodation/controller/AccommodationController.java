package com.travelmaker.yeoga.accommodation.controller;

import com.travelmaker.yeoga.accommodation.model.Accommodation;
import com.travelmaker.yeoga.accommodation.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Autowired
    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    @GetMapping("/accommodations/top-visited")
    public ResponseEntity<List<Accommodation>> getTopVisitedAccommodations(@RequestParam int limit) {
        List<Accommodation> accommodations = accommodationService.getTopVisitedAccommodations(limit);
        return ResponseEntity.ok(accommodations);
    }
}

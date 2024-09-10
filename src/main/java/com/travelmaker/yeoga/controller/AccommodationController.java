package com.travelmaker.yeoga.controller;

import com.travelmaker.yeoga.model.Accommodation;
import com.travelmaker.yeoga.service.AccommodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccommodationController {

    private final AccommodationService accommodationService;


    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }
    @GetMapping("/accommodations/top-visited")
    public ResponseEntity<List<Accommodation>> getTopVisitedAccommodations(@RequestParam int limit) {
        List<Accommodation> accommodations = accommodationService.getTopVisitedAccommodations(limit);
        return ResponseEntity.ok(accommodations);
    }
}

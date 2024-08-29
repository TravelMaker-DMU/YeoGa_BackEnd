package com.travelmaker.yeoga.attraction.controller;

import com.travelmaker.yeoga.attraction.model.Attraction;
import com.travelmaker.yeoga.attraction.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttractionController {


    private final AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping("/attractions/nearby")
    public List<Attraction> getNearbyAttractions(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam double radius) {
        return attractionService.findNearbyAttractions(lat, lon, radius);
    }
}

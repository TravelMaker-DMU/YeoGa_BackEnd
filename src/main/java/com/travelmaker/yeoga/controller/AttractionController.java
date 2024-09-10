package com.travelmaker.yeoga.controller;

import com.travelmaker.yeoga.model.Attraction;
import com.travelmaker.yeoga.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AttractionController {


    private final AttractionService attractionService;


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

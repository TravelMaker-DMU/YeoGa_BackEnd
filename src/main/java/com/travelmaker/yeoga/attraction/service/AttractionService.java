package com.travelmaker.yeoga.attraction.service;

import com.travelmaker.yeoga.attraction.model.Attraction;
import com.travelmaker.yeoga.attraction.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionService {


    private final AttractionRepository attractionRepository;

    @Autowired
    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public List<Attraction> findNearbyAttractions(double lat, double lon, double radius) {
        return attractionRepository.findNearbyAttractions(lat, lon, radius);
    }
}
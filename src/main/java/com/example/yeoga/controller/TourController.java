package com.example.yeoga.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TourController {

    private final RestTemplate restTemplate;

    @Value("${tour.api.key}")
    private String apiKey;

    @Value("${tour.api.base-url}")
    private String baseUrl;

    public TourController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/tour")
    public ResponseEntity<String> getTourData() {
        int PageNo = ThreadLocalRandom.current().nextInt(1, 550);
        String url = baseUrl + "?ServiceKey=" + apiKey
                     + "&MobileApp=AppTest"
                     + "&MobileOS=ETC"
                     + "&pageNo=" + PageNo
                     + "&numOfRows=4"
                     + "&arrange=A"
                     + "&_type=json";

        try {
            String response = restTemplate.getForObject(url, String.class);
            if (response == null || response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No data found.");
            }
            return ResponseEntity.ok(response);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("API request failed: " + e.getMessage());
        }
    }
}
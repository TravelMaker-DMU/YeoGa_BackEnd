package com.example.yeoga.openapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LodgingController {
    private final RestTemplate restTemplate;

    @Autowired
    private LodgingController lodgingController;

    @Value("${trip_news.api.key}")
    private String apiKey;

    @Value("${google_places.api.base-url}")
    private String baseUrl;

    public LodgingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/Lodging")
    public ResponseEntity<String> getLodging(
            @RequestParam String location,
            @RequestParam(defaultValue = "1500") int radius) {

        String url = baseUrl + "/nearbysearch/json"
                + "?key=" + apiKey
                + "&location=" + location
                + "&radius=" + radius
                + "&type=lodging"
                + "&language=ko";

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
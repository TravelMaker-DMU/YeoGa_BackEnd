package com.example.yeoga.openapi;

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
public class TourCoursesController {
    private final RestTemplate restTemplate;

    // @Value 어노테이션을 사용하여 application.properties에서 값을 주입받음
    @Value("${tour_course.api.key}")
    private String apiKey;

    @Value("${tour_course.api.base-url}")
    private String baseUrl;

    public TourCoursesController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/tourcourses")
    public ResponseEntity<String> getTourCourses() {
        int pageNo = ThreadLocalRandom.current().nextInt(1, 50);
        String url = baseUrl + "?ServiceKey=" + apiKey
                + "&MobileApp=AppTest"
                + "&MobileOS=ETC"
                + "&PageNo=" + pageNo
                + "&numOfRows=10"
                + "&_type=json"
                + "&contentTypeId=25";

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
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class TripNewsController {
    private final RestTemplate restTemplate;

    // 프로퍼티 값을 주입받는 필드
    @Value("${trip_news.api.key}")
    private String apiKey;

    @Value("${trip_news.api.base-url}")
    private String baseUrl;

    public TripNewsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/tripnews")
    public ResponseEntity<String> getTripNews() {
        String eventStartDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        int pageNo = ThreadLocalRandom.current().nextInt(1, 5);
        String url = baseUrl + "?ServiceKey=" + apiKey
                + "&MobileApp=AppTest"
                + "&MobileOS=ETC"
                + "&PageNo=" + pageNo
                + "&numOfRows=10"
                + "&_type=json"
                + "&eventStartDate=" + eventStartDate;

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
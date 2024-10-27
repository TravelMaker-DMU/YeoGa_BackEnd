package com.example.yeoga.openapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class KakaoController {
    private final RestTemplate restTemplate;

    @Autowired
    public KakaoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${kakao.api.key}")
    private String apiKey;

    @Value("${kakao.api.base-url}")
    private String baseUrl;

    @GetMapping("/kakaomaps")
    public ResponseEntity<String> getKakaomaps(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestBody List<String> waypoints
    ) {
        String url = baseUrl + "/v1/waypoints/directions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK " + apiKey);

        Map<String, Object> body = new HashMap<>();
        body.put("origin", origin);
        body.put("destination", destination);
        body.put("waypoints", waypoints);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            // API 호출
            ResponseEntity<String> response = restTemplate.exchange(
                    url, HttpMethod.POST, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode())
                        .body("Failed to retrieve directions: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("API request failed: " + e.getMessage());
        }
    }
}
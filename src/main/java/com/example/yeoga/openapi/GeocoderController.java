package com.example.yeoga.openapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class GeocoderController {

    private final RestTemplate restTemplate;

    // 프로퍼티 값을 주입받는 필드
    @Value("${VWORLD_API_KEY}")
    private String apiKey;

    @Value("${VWORLD_API_BASE_URL}")
    private String baseUrl;

    public GeocoderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 주소를 좌표로 변환하는 API 엔드포인트
     * 예시: /api/geocode?address=서울특별시 강남구 봉은사로 524&type=ROAD
     *
     * @param address 변환할 주소
     * @param type    주소 유형 (ROAD 또는 PARCEL)
     * @return 좌표 정보 또는 에러 메시지
     */
    @GetMapping("/geocode")
    public ResponseEntity<String> geocode(
            @RequestParam String address,
            @RequestParam(defaultValue = "ROAD") String type) {

        // URL 인코딩 처리
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = String.format("%s?service=address&request=GetCoord&key=%s&type=%s&address=%s&format=json",
                baseUrl, apiKey, type.toUpperCase(), encodedAddress);

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

    /**
     * 주소를 좌표로 변환하고, 추가적인 파라미터를 받을 수 있는 엔드포인트 예시
     * 예시: /api/geocode/advanced?address=...&type=ROAD&crs=EPSG:4326&refine=true&simple=false
     *
     * @param address    변환할 주소
     * @param type       주소 유형 (ROAD 또는 PARCEL)
     * @param crs        응답 좌표계 (기본값: EPSG:4326)
     * @param refine     주소 정제 여부 (기본값: true)
     * @param simple     응답 간략 출력 여부 (기본값: false)
     * @return 좌표 정보 또는 에러 메시지
     */
    @GetMapping("/geocode/advanced")
    public ResponseEntity<String> advancedGeocode(
            @RequestParam String address,
            @RequestParam(defaultValue = "ROAD") String type,
            @RequestParam(defaultValue = "EPSG:4326") String crs,
            @RequestParam(defaultValue = "true") boolean refine,
            @RequestParam(defaultValue = "false") boolean simple) {

        // URL 인코딩 처리
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String url = String.format("%s?service=address&request=GetCoord&key=%s&type=%s&address=%s&format=json&crs=%s&refine=%s&simple=%s",
                baseUrl, apiKey, type.toUpperCase(), encodedAddress, crs, refine, simple);

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
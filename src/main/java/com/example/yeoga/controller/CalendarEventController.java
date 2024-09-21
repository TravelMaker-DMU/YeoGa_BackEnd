package com.example.yeoga.controller;

import com.example.yeoga.dto.CalendarEventDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.CalendarEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/my/{username}")
public class CalendarEventController {

    private final CalendarEventService calendarEventService;

    public CalendarEventController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    // 캘린더 이벤트 생성
    @PostMapping("/calendar")
    public ResponseEntity<?> createEvent(@RequestBody CalendarEventDTO eventDTO, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        CalendarEventDTO createdEvent = calendarEventService.createEvent(user, eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

    // 사용자의 캘린더 이벤트 목록 조회
    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarEventDTO>> getEvents(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<CalendarEventDTO> events = calendarEventService.getEvents(user);
        return ResponseEntity.ok(events);
    }

    // 특정 이벤트 조회
    @GetMapping("/calendar/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable int eventId) {
        Optional<CalendarEventDTO> event = calendarEventService.getEventById(eventId);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.status(404).body("Event not found.");
        }
    }

    // 이벤트 삭제
    @DeleteMapping("/calendar/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
        calendarEventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully.");
    }
}
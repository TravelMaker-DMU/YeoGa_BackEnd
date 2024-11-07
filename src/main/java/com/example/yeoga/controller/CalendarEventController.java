package com.example.yeoga.controller;

import com.example.yeoga.dto.CalendarEventDTO;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.service.CalendarEventService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(description = "캘린더 이벤트를 추가하는 API", summary = "캘린더 이벤트 추가")
    @PostMapping("/calendar")
    public ResponseEntity<?> createEvent(@RequestBody CalendarEventDTO eventDTO, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        CalendarEventDTO createdEvent = calendarEventService.createEvent(user, eventDTO);
        return ResponseEntity.ok(createdEvent);
    }

    @Operation(description = "사용자의 캘린더 이벤트 목록을 조회하는 API", summary = "캘린더 이벤트 조회")
    @GetMapping("/calendar")
    public ResponseEntity<List<CalendarEventDTO>> getEvents(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        List<CalendarEventDTO> events = calendarEventService.getEvents(user);
        return ResponseEntity.ok(events);
    }

    @Operation(description = "특정 이벤트를 조회하기 위한 API. 이벤트 아이디로 조회가능", summary = "특정 이벤트 조회")
    @GetMapping("/calendar/{eventId}")
    public ResponseEntity<?> getEventById(@PathVariable int eventId) {
        Optional<CalendarEventDTO> event = calendarEventService.getEventById(eventId);
        if (event.isPresent()) {
            return ResponseEntity.ok(event.get());
        } else {
            return ResponseEntity.status(404).body("Event not found.");
        }
    }

    @Operation(description = "캘린더 이벤트를 삭제하기 위한 API", summary = "캘린더 이벤트 삭제")
    @DeleteMapping("/calendar/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) {
        calendarEventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully.");
    }
}
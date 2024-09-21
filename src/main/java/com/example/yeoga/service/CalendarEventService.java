// package: com.example.springjwt.service

package com.example.yeoga.service;

import com.example.yeoga.dto.CalendarEventDTO;
import com.example.yeoga.entity.CalendarEventEntity;
import com.example.yeoga.entity.UserEntity;
import com.example.yeoga.repository.CalendarEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    public CalendarEventDTO createEvent(UserEntity user, CalendarEventDTO eventDTO) {
        CalendarEventEntity event = convertToEntity(eventDTO);
        event.setUser(user);
        CalendarEventEntity savedEvent = calendarEventRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public List<CalendarEventDTO> getEvents(UserEntity user) {
        List<CalendarEventEntity> events = calendarEventRepository.findByUser(user);
        return events.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<CalendarEventDTO> getEventById(int eventId) {
        Optional<CalendarEventEntity> event = calendarEventRepository.findById(eventId);
        return event.map(this::convertToDTO);
    }

    public void deleteEvent(int eventId) {
        calendarEventRepository.deleteById(eventId);
    }

    private CalendarEventDTO convertToDTO(CalendarEventEntity entity) {
        CalendarEventDTO dto = new CalendarEventDTO();
        dto.setEventId(entity.getEventId());
        dto.setTitle(entity.getTitle());
        dto.setEventDate(entity.getEventDate());
        return dto;
    }

    private CalendarEventEntity convertToEntity(CalendarEventDTO dto) {
        CalendarEventEntity entity = new CalendarEventEntity();
        entity.setTitle(dto.getTitle());
        entity.setEventDate(dto.getEventDate());
        return entity;
    }
}
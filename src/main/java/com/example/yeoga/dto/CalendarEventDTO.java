
package com.example.yeoga.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CalendarEventDTO {

    private int eventId;
    private String title;
    private LocalDate eventDate;
}
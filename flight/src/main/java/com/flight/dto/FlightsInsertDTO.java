package com.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class FlightsInsertDTO {
    private String fromLocation;
    private String toLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Integer> daysOfWeek; // 1: Pazartesi, 2: Salı, .....  7: Pazar
    private Integer capacity;
}
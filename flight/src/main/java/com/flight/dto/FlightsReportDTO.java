package com.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class FlightsReportDTO {
    private String fromLocation;
    private String toLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;
}
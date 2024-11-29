package com.flight.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FlightsResponseDTO {
    private String fromLocation;
    private String toLocation;
    private LocalDate startDate;
    private LocalDate endDate;
}
package com.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BuyTicketDTO {
    private String ticketId;
    private String passengerName;
    private LocalDate startDate;
    private String fromLocation;
    private String toLocation;
}
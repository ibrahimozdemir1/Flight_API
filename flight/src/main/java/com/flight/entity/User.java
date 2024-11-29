package com.flight.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    private String id;

    private String ticketId;
    private String passengerName;
    private boolean checkIn;
    private String fromLocation;
    private String toLocation;
}
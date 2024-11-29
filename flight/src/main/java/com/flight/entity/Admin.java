package com.flight.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Admin {

    @Id
    private String id;

    private String fromLocation;
    private String toLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer capacity;
}
package com.flight.api;

import com.flight.dto.FlightsReportDTO;
import com.flight.dto.FlightsInsertDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface AdminApi {
    String PATH_CREATE_FLIGHTS = "/flights";
    String PATH_GET_REPORT_FLIGHTS_LIST ="/report-flights-list";

    @PostMapping(PATH_CREATE_FLIGHTS)
    String insertFlights(@RequestBody FlightsInsertDTO flightRequest);

    @GetMapping(PATH_GET_REPORT_FLIGHTS_LIST)
    Page<FlightsReportDTO> reportFlights(@RequestParam String fromLocation,
                                         @RequestParam String toLocation,
                                         @RequestParam LocalDate startDate,
                                         @RequestParam LocalDate endDate,
                                         @RequestParam Integer capacity,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size);

}
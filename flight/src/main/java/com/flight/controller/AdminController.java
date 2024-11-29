package com.flight.controller;

import com.flight.api.AdminApi;
import com.flight.dto.FlightsReportDTO;
import com.flight.dto.FlightsInsertDTO;
import com.flight.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController implements AdminApi {
    private final AdminService adminService;

    @Override
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String insertFlights(FlightsInsertDTO flightRequest) {
        return adminService.insertFlights(flightRequest);
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Page<FlightsReportDTO> reportFlights(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam Integer capacity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        FlightsReportDTO flightsReportDTO = new FlightsReportDTO(fromLocation, toLocation, startDate, endDate, capacity);

        return adminService.reportFlights(flightsReportDTO, page, size);
    }
}
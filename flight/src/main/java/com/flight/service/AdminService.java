package com.flight.service;

import com.flight.dto.FlightsReportDTO;
import com.flight.dto.FlightsInsertDTO;
import com.flight.entity.Admin;
import com.flight.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public String insertFlights(FlightsInsertDTO flightRequest) {
        try {
            String from = flightRequest.getFromLocation();
            String to = flightRequest.getToLocation();
            LocalDate startDate = flightRequest.getStartDate();
            LocalDate endDate = flightRequest.getEndDate();
            List<Integer> daysOfWeek = flightRequest.getDaysOfWeek();
            Integer capacity = flightRequest.getCapacity();

            List<LocalDate> recurringDates = getRecurringDates(startDate, endDate, daysOfWeek);

            for (LocalDate date : recurringDates) {
                Admin flight = new Admin();
                flight.setFromLocation(from);
                flight.setToLocation(to);
                flight.setStartDate(date);
                flight.setEndDate(date.plusDays(1));
                flight.setCapacity(capacity);
                adminRepository.save(flight);
            }

            return "Flights successfully created!";
        } catch (Exception e) {
            return "Error creating flights: " + e.getMessage();
        }
    }

    private List<LocalDate> getRecurringDates(LocalDate startDate, LocalDate endDate, List<Integer> daysOfWeek) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (daysOfWeek.contains(currentDate.getDayOfWeek().getValue())) {
                dates.add(currentDate);
            }
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }


    public Page<FlightsReportDTO> reportFlights(FlightsReportDTO flightsReportDTO, int page, int size) {
        String fromLocation = flightsReportDTO.getFromLocation();
        String toLocation = flightsReportDTO.getToLocation();
        LocalDate startDate = flightsReportDTO.getStartDate();
        LocalDate endDate = flightsReportDTO.getEndDate();
        Integer capacity = flightsReportDTO.getCapacity();

        Pageable pageable = PageRequest.of(page, size);

        Page<Admin> flights = adminRepository.findByFromLocationAndToLocationAndStartDateBetweenAndCapacityGreaterThanEqual(
                fromLocation, toLocation, startDate, endDate, capacity, pageable);

        if (flights.isEmpty()) {
            return Page.empty();
        }

        return flights.map(flight -> new FlightsReportDTO(
                flight.getFromLocation(),
                flight.getToLocation(),
                flight.getStartDate(),
                flight.getEndDate(),
                flight.getCapacity()
        ));
    }

}
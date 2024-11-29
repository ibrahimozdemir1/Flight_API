package com.flight.service;

import com.flight.dto.BuyTicketDTO;
import com.flight.dto.CheckInDTO;
import com.flight.dto.FlightsResponseDTO;
import com.flight.dto.FlightsQueryDTO;
import com.flight.entity.Admin;
import com.flight.entity.User;
import com.flight.repository.AdminRepository;
import com.flight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;


    public Page<FlightsResponseDTO> queryFlights(FlightsQueryDTO flightsQueryDTO, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Admin> flights = adminRepository.findByFromLocationAndToLocationAndStartDateBetweenAndCapacityGreaterThan(
                flightsQueryDTO.getFromLocation(),
                flightsQueryDTO.getToLocation(),
                flightsQueryDTO.getStartDate(),
                flightsQueryDTO.getEndDate(),
                0,
                pageable
        );

        return flights.map(flight -> {
            FlightsResponseDTO dto = new FlightsResponseDTO();
            dto.setFromLocation(flight.getFromLocation());
            dto.setToLocation(flight.getToLocation());
            dto.setStartDate(flight.getStartDate());
            dto.setEndDate(flight.getEndDate());
            return dto;
        });
    }


    public String buyTicket(BuyTicketDTO buyTicketDTO) {
        if (userRepository.existsByTicketId(buyTicketDTO.getTicketId())) {
            return "Error: Ticket ID already in use. Please provide a different Ticket ID.";
        }

        LocalDate startDate = buyTicketDTO.getStartDate();
        List<Admin> flights = adminRepository.findByStartDate(startDate);

        if (flights.isEmpty()) {
            return "Error: No flights available in the given date range.";
        }

        Admin availableFlight = null;
        for (Admin flight : flights) {
            if (flight.getCapacity() > 0) {
                availableFlight = flight;
                break;
            }
        }

        if (availableFlight == null) {
            return "Error: No flights with available capacity.";
        }

        User user = new User();
        user.setTicketId(buyTicketDTO.getTicketId());
        user.setPassengerName(buyTicketDTO.getPassengerName());
        user.setFromLocation(buyTicketDTO.getFromLocation());
        user.setToLocation(buyTicketDTO.getToLocation());
        user.setCheckIn(false);
        userRepository.save(user);

        availableFlight.setCapacity(availableFlight.getCapacity() - 1);
        adminRepository.save(availableFlight);

        return "Successful: Ticket booked successfully.";
    }



    public String checkIn(CheckInDTO checkInDTO) {
        User user = userRepository.findByTicketId(checkInDTO.getTicketId());

        if (user == null) {
            return "Error: Ticket not found.";
        }

        if (user.isCheckIn()) {
            return "Error: Ticket already checked-in.";
        }

        user.setCheckIn(true);
        userRepository.save(user);

        return "Successful: Check-in completed.";
    }
}
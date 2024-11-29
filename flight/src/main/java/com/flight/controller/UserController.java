package com.flight.controller;

import com.flight.api.UserApi;
import com.flight.dto.BuyTicketDTO;
import com.flight.dto.CheckInDTO;
import com.flight.dto.FlightsResponseDTO;
import com.flight.dto.FlightsQueryDTO;
import com.flight.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public Page<FlightsResponseDTO> queryFlights(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        FlightsQueryDTO flightsQueryDTO = new FlightsQueryDTO(fromLocation, toLocation, startDate, endDate);
        return userService.queryFlights(flightsQueryDTO, page, size);
    }

    @Override
    public String buyTicket(BuyTicketDTO buyTicketDTO) {
        return userService.buyTicket(buyTicketDTO);
    }

    @Override
    public String checkIn(CheckInDTO checkInDTO) {
        return userService.checkIn(checkInDTO);
    }
}
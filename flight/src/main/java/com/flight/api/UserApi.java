package com.flight.api;

import com.flight.dto.BuyTicketDTO;
import com.flight.dto.CheckInDTO;
import com.flight.dto.FlightsResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

public interface UserApi {
    String PATH_QUERY_FLIGHTS = "/query-flights";
    String PATH_BUY_TICKET = "/buy-ticket";
    String PATH_CHECK_IN = "/check-in";

    @GetMapping(PATH_QUERY_FLIGHTS)
    Page<FlightsResponseDTO> queryFlights(@RequestParam String fromLocation,
                                          @RequestParam String toLocation,
                                          @RequestParam LocalDate startDate,
                                          @RequestParam LocalDate endDate,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size);

    @PostMapping(PATH_BUY_TICKET)
    String buyTicket(@RequestBody BuyTicketDTO buyTicketDTO);

    @PostMapping(PATH_CHECK_IN)
    String checkIn(@RequestBody CheckInDTO checkInDTO);
}
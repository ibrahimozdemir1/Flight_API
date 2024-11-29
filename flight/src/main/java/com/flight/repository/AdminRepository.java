package com.flight.repository;

import com.flight.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AdminRepository extends MongoRepository<Admin, String> {
    Page<Admin> findByFromLocationAndToLocationAndStartDateBetweenAndCapacityGreaterThanEqual(
            String fromLocation,
            String toLocation,
            LocalDate startDate,
            LocalDate endDate,
            Integer capacity,
            Pageable pageable);

    List<Admin> findByStartDate(LocalDate startDate);

    Page<Admin> findByFromLocationAndToLocationAndStartDateBetweenAndCapacityGreaterThan(
            String fromLocation,
            String toLocation,
            LocalDate startDate,
            LocalDate endDate,
            Integer capacity,
            Pageable pageable);
}
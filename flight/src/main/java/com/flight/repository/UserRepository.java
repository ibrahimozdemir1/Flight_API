package com.flight.repository;

import com.flight.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByTicketId(String ticketId);

    boolean existsByTicketId(String ticketId);

}
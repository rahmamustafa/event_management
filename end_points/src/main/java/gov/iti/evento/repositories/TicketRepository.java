package gov.iti.evento.repositories;

import gov.iti.evento.entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByType(String type);
}
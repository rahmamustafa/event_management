package gov.iti.evento.repositories;

import gov.iti.evento.entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
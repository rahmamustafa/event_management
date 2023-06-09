package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.entites.EventTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTicketRepository extends JpaRepository<EventTicket, EventTicketId> {
}
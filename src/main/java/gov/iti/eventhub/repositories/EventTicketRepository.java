package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.EventTicket;
import gov.iti.eventhub.entites.EventTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTicketRepository extends JpaRepository<EventTicket, EventTicketId> {
}
package gov.iti.evento.repositories;

import gov.iti.evento.entites.AvailableTicket;
import gov.iti.evento.entites.AvailableTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableTicketRepository extends JpaRepository<AvailableTicket, AvailableTicketId> {

    public int getAvailableTicketById(AvailableTicketId availableTicketId);
}
package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.UserTicket;
import gov.iti.eventhub.entites.UserTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket, UserTicketId> {
}
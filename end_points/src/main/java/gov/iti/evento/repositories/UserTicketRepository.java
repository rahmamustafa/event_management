package gov.iti.evento.repositories;

import gov.iti.evento.entites.UserTicket;
import gov.iti.evento.entites.UserTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTicketRepository extends JpaRepository<UserTicket, UserTicketId> {
}
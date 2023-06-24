package gov.iti.evento.repositories;

import gov.iti.evento.entites.UserTicket;
import gov.iti.evento.entites.UserTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, UserTicketId> {
}
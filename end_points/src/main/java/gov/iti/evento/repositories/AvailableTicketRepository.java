package gov.iti.evento.repositories;

import gov.iti.evento.entites.AvailableTicket;
import gov.iti.evento.entites.AvailableTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AvailableTicketRepository extends JpaRepository<AvailableTicket, AvailableTicketId> {
    @Transactional
    @Modifying
    @Query("update AvailableTicket a set a.availableTickets = ?1 where a.id = ?2")
    int updateAvailableTicketsById(Integer availableTickets, AvailableTicketId id);

    public AvailableTicket getAvailableTicketById(AvailableTicketId availableTicketId);
//    @Query("update AvailableTicket a set a.availableTickets = ?1 where a.id = ?2 ")
//    public void updateAvailableTicketsById(int availableTickets, AvailableTicketId availableTicketId );
}
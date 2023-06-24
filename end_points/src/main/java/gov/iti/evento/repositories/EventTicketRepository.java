package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.entites.EventTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventTicketRepository extends JpaRepository<EventTicket, EventTicketId> {
    public List<EventTicket> getEventTicketByEvent_Id(int id);

}
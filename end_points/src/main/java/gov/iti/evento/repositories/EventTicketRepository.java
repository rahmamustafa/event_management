package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.entites.EventTicketId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventTicketRepository extends JpaRepository<EventTicket, EventTicketId> {
    public List<EventTicket> getEventTicketByEvent_Id(int id);
    public EventTicket getPriceByEventTicketId(EventTicketId eventTicketId);

}
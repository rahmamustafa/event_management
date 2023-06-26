package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.entites.EventTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventTicketRepository extends JpaRepository<EventTicket, EventTicketId> {
    public List<EventTicket> getEventTicketByEvent_Id(int id);
    public EventTicket getPriceByEventTicketId(EventTicketId eventTicketId);
    @Query("select e.id from EventTicket e where e.eventTicketId =?1")
    public int getIdById(EventTicketId eventTicketId);
    public EventTicket getEventTicketByEventTicketId(EventTicketId eventTicketId);



}
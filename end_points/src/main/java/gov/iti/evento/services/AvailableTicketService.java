package gov.iti.evento.services;

import gov.iti.evento.entites.AvailableTicket;
import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.AvailableTicketRepository;
import gov.iti.evento.repositories.TicketRepository;
import gov.iti.evento.services.util.enums.EventTicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvailableTicketService {
    private final TicketRepository ticketRepository;
    private final AvailableTicketRepository availableTicketRepository;
    public void saveAvailableTicket(Event event , EventTicketType eventTicketType , int availableNumber){
        AvailableTicket eventTicket = new AvailableTicket();
        eventTicket.setEvent(event);
        eventTicket.setTicket(ticketRepository.findByType(eventTicketType.name()).orElseThrow());
        eventTicket.setAvailableTickets(availableNumber);
        availableTicketRepository.save(eventTicket);
    }
}

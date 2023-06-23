package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.repositories.EventTicketRepository;
import gov.iti.evento.repositories.TicketRepository;
import gov.iti.evento.services.dtos.ticket.EventTicketDto;
import gov.iti.evento.services.mappers.ticket.EventTicketMapper;
import gov.iti.evento.services.util.enums.EventTicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventTicketService {
    @Autowired
    EventTicketMapper eventTicketMapper;
    @Autowired
    EventTicketRepository eventTicketRepository;

    @Autowired
    TicketRepository ticketRepository;
    public List<EventTicketDto> getEventTicketDetails(int eventId){

        List<EventTicket> eventTicket=eventTicketRepository.getEventTicketByEvent_Id(eventId);

        List<EventTicketDto> eventTicketDtos = new ArrayList<>();
        for (EventTicket e: eventTicket
             ) {
            eventTicketDtos.add(eventTicketMapper.toDto(e));
        }
        return eventTicketDtos;
    }

    public void saveEventTicket(Event event , EventTicketType eventTicketType , Float price){
        EventTicket eventTicket = new EventTicket();
        eventTicket.setEvent(event);
        eventTicket.setTicket(ticketRepository.findByType(eventTicketType.name()).orElseThrow());
        eventTicket.setPrice(price);
        eventTicketRepository.save(eventTicket);
    }

}

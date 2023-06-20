package gov.iti.evento.services;

import gov.iti.evento.entites.EventTicket;
import gov.iti.evento.repositories.EventTicketRepository;
import gov.iti.evento.services.dtos.ticket.EventTicketDto;
import gov.iti.evento.services.mappers.ticket.EventTicketMapper;
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
    public List<EventTicketDto> getEventTicketDetails(int eventId){

        List<EventTicket> eventTicket=eventTicketRepository.getEventTicketByEvent_Id(eventId);

        List<EventTicketDto> eventTicketDtos = new ArrayList<>();
        for (EventTicket e: eventTicket
             ) {
            eventTicketDtos.add(eventTicketMapper.toDto(e));
        }
        return eventTicketDtos;
    }

}

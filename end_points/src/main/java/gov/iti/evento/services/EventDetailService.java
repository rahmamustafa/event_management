package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.dtos.EventoDetailesDTO;
import gov.iti.evento.services.mappers.EventDisplayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class EventDetailService {
    private final EventRepository eventRepository;
    private final EventDisplayMapper eventDisplayMapper;

    @Autowired
    public EventDetailService(EventRepository eventRepository, EventDisplayMapper eventDisplayMapper){
        this.eventRepository=eventRepository;
        this.eventDisplayMapper=eventDisplayMapper;
    }

    public EventoDetailesDTO getEvent (Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new NotFoundException("Event not found"));
        return eventDisplayMapper.eventToEventDetailsDTO(event);
    }
}

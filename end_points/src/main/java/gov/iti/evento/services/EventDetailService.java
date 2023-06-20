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
    private EventRepository eventRepository;
    @Autowired
    private EventDisplayMapper eventDisplayMapper;

    @Autowired
    public EventDetailService(EventRepository eventRepository){
        this.eventRepository=eventRepository;
        // this.eventDisplayMapper=eventDisplayMapper;
    }

    public EventoDetailesDTO getEvent (Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(()-> new NotFoundException("Event not found"));
        System.out.println(event.getEventDate());
        return eventDisplayMapper.eventToEventDetailsDTO(event);
    }

    public Event savaEvent (Event event){
        return eventRepository.save(event);
    }
}

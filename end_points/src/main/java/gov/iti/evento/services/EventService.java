package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventMapper eventMapper;

   public List<EventDto> getAllEvents() {
        List<Event> events = Collections.unmodifiableList(eventRepository.findAll());
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }
}

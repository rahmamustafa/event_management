package gov.iti.evento.services;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.mappers.EventMapper;
@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    
    public Page<EventByDateDto> getEventsByDate(LocalDate date, Pageable pageable) {
        Page<Event> eventPage=eventRepository.findByDate(date, pageable);
        eventPage.forEach(eventpage ->System.out.println("titel ->"+eventpage.getTitle()));
        return EventMapper.INSTANCE.map(eventPage);
    }
}

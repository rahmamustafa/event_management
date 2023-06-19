package gov.iti.evento.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;


import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.mappers.EventMapper;
import gov.iti.evento.services.util.exceptions.MessageException;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    EventSpeakerRepository eventSpeakerRepository;

    @Autowired
    EventMapper eventMapper;

    public List<EventDto> getAllEvents() {
        List<Event> events = Collections.unmodifiableList(eventRepository.findAll());
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }

    public List<EventDto> getEventByCategoryType(String categoryType) throws MessageException {
        Category category = categoryRepository.findCategoryByType(categoryType);
        if (category == null) throw new MessageException("Category Not Found");
        List<Event> events = Collections.unmodifiableList(eventRepository.findByCategoryType(categoryType));
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }

    public List<EventDto> getEventBySpeaker(String speaker) {
        List<EventSpeaker> eventSpeakers = eventSpeakerRepository.findBySpeakerNameIgnoreCaseLike(speaker);
        List<Event> events = eventSpeakers.stream().map(EventSpeaker::getEvent).collect(Collectors.toList());
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }
  
    
    public Page<EventByDateDto> getEventsByDate(LocalDate date, Pageable pageable) {
        Page<Event> eventPage=eventRepository.findByDate(date, pageable);
        eventPage.forEach(eventpage ->System.out.println("titel ->"+eventpage.getTitle()));
        return EventMapper.INSTANCE.map(eventPage);
    }
}

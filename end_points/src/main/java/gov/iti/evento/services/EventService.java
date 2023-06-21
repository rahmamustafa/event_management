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
import gov.iti.evento.services.dtos.NewEventsDto;
import gov.iti.evento.services.mappers.EventMapper;
import gov.iti.evento.services.util.exceptions.MessageException;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;

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

    public List<EventDto> getEvents(int page, int size) throws Exception {
        System.out.println("Page : " + page + " Size : " + size);
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Event> events = eventRepository.findAll(pageRequest).getContent();
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = eventMapper.INSTANCE.toDto(event);
            list.add(eventDto);
        }
        return list;
    }

    public List<EventDto> getEventByCategoryType(String categoryType, int page) throws Exception {
        Category category = categoryRepository.findCategoryByType(categoryType);
        if (category == null) throw new MessageException("Category Not Found");
        PageRequest pageRequest = PageRequest.of(page, 6);
        List<Event> events = Collections.unmodifiableList(eventRepository.findByCategoryType(categoryType, pageRequest));
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = eventMapper.INSTANCE.toDto(event);
            list.add(eventDto);
        }
        return list;
    }

    public List<EventDto> getEventBySpeaker(String speaker, int page) throws Exception {
        PageRequest pageRequest = PageRequest.of(page, 6);
        List<EventSpeaker> eventSpeakers = eventSpeakerRepository.findBySpeakerNameIgnoreCaseLike(speaker, pageRequest);
        List<Event> events = eventSpeakers.stream().map(EventSpeaker::getEvent).collect(Collectors.toList());
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = eventMapper.INSTANCE.toDto(event);
            list.add(eventDto);
        }
        return list;
    }

    public List<EventDto> getEventByStatus(String status, int page) throws Exception {
        PageRequest pageRequest = PageRequest.of(page, 6);
        List<Event> events = Collections.unmodifiableList(eventRepository.findByStatus(status, pageRequest));
        List<EventDto> list = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = eventMapper.INSTANCE.toDto(event);
            list.add(eventDto);
        }
        return list;
    }
  
    
    public Page<EventByDateDto> getEventsByDate(LocalDate date, Pageable pageable) {
        Page<Event> eventPage=eventRepository.findByDate(date, pageable);
    
        return EventMapper.INSTANCE.map(eventPage);
    }

     public List<NewEventsDto> getLastNewEvents() {
        List<Event> events= eventRepository.findTop3ByOrderByEventDateDesc();

        return events.stream().map(eventMapper.INSTANCE::toNewEventDto).toList();
    }
}

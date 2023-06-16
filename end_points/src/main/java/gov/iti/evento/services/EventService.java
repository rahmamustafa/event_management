package gov.iti.evento.services;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.mappers.EventMapper;
import gov.iti.evento.services.util.exceptions.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Page<EventDto> getEvents(int page, int size) {
        System.out.println("Page : " + page + " Size : " + size );
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Event> eventPage = eventRepository.findAll(pageRequest);
        return eventPage.map(eventMapper.INSTANCE::toDto);
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

    public List<EventDto> getEventByStatus(String status) {
        List<Event> events = Collections.unmodifiableList(eventRepository.findByStatus(status));
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }
}

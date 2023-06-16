package gov.iti.evento.services;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.mappers.EventMapper;
import gov.iti.evento.services.util.exceptions.MessageException;
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
    CategoryRepository categoryRepository;

    @Autowired
    EventMapper eventMapper;

   public List<EventDto> getAllEvents() {
       List<Event> events = Collections.unmodifiableList(eventRepository.findAll());
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }
    public List<EventDto> getEventByCategoryType(String categoryType) throws MessageException {
        Category category = categoryRepository.findCategoryByType(categoryType);
        if(category==null)
            throw new MessageException("Category Not Found");
        List<Event> events = Collections.unmodifiableList(eventRepository.findByCategoryType(categoryType));
        return events.stream().map(eventMapper.INSTANCE::toDto).toList();
    }
}

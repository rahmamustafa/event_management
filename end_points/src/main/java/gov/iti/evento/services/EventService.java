package gov.iti.evento.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import gov.iti.evento.services.dtos.UserTicketDto;
import gov.iti.evento.services.dtos.event.EventCalendarDto;
import org.springframework.beans.factory.annotation.Autowired;
import gov.iti.evento.entites.*;
import gov.iti.evento.repositories.*;
import gov.iti.evento.services.dtos.event.AddEventDto;
import gov.iti.evento.services.util.enums.EventTicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.dtos.NewEventsDto;
import gov.iti.evento.services.mappers.EventMapper;
import gov.iti.evento.services.util.exceptions.MessageException;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final EventSpeakerRepository eventSpeakerRepository;
    private final EventMapper eventMapper;
    private final EventTicketRepository eventTicketRepository;
    private final TicketRepository ticketRepository;
    private final EventTicketService eventTicketService;
    private final AvailableTicketService availableTicketService;
    private final SpeakerRepository speakerRepository;


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

    public void saveEvent(AddEventDto addEventDto){

        Event event = eventMapper.toEntity(addEventDto);
        event.setCategory(categoryRepository.findCategoryByTypeIgnoreCase(addEventDto.getCategory()));
        eventRepository.save(event);

        eventTicketService.saveEventTicket(event, EventTicketType.STANDARD
                ,addEventDto.getTicketPrice().get(EventTicketType.STANDARD.name()));
        eventTicketService.saveEventTicket(event, EventTicketType.PLATINUM
                ,addEventDto.getTicketPrice().get(EventTicketType.PLATINUM.name()));
        eventTicketService.saveEventTicket(event, EventTicketType.STARTER
                ,addEventDto.getTicketPrice().get(EventTicketType.STARTER.name()));


        availableTicketService.saveAvailableTicket(event,EventTicketType.STANDARD,
                addEventDto.getTicketNumber().get(EventTicketType.STANDARD.name()));
        availableTicketService.saveAvailableTicket(event,EventTicketType.STARTER,
                addEventDto.getTicketNumber().get(EventTicketType.STARTER.name()));
        availableTicketService.saveAvailableTicket(event,EventTicketType.PLATINUM,
                addEventDto.getTicketNumber().get(EventTicketType.PLATINUM.name()));

        addEventDto.getSpeakers().stream()
                .forEach(speakerId -> eventSpeakerRepository
                                        .save(new EventSpeaker(event,speakerRepository.findById(speakerId).orElseThrow())));


    }

    public List<EventDto> getEventByCategoryType(String categoryType, int page) throws Exception {
        Category category = categoryRepository.findCategoryByTypeIgnoreCase(categoryType);
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
    public Optional<Event> findEventById (Integer id){
        return eventRepository.findById(id);
    }

    public boolean checkTitleValid(String title) {
        return eventRepository.existsByTitleIgnoreCase(title);

    }

    public List<EventCalendarDto> findAllEvents() {
         return eventRepository.findAll().stream().map(eventMapper.INSTANCE::toEventCalendarDto).toList();
    }

    public long getNumberOfPages(int pageSize) {
        long count=eventRepository.count();
        if(eventRepository.count()%pageSize !=0)

            return (long) (count/pageSize)+1;
        else
            return (long) (count/pageSize);
    }
}

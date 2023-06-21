package gov.iti.evento.controllers;

import gov.iti.evento.entites.Category;
import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.CategoryService;
import gov.iti.evento.services.EventReviewService;
import gov.iti.evento.services.EventTicketService;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.dtos.ticket.EventTicketDto;
import gov.iti.evento.services.dtos.NewEventsDto;
import gov.iti.evento.services.util.exceptions.MessageException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.StackWalker.Option;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    private EventReviewService eventReviewService;
    @Autowired
    private EventTicketService eventTicketService;

    @Autowired
    EventService eventService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired

      @GetMapping("/events/create")
     public  int createNewEvent(){
    
       
         
        Event event=new Event();
        Optional<Category> A =categoryRepository.findById(1);
        event.setCategory(A.get());
        event.setDescription("null");
        event.setIsOnline(Byte.parseByte("0"));
        event.setLocation("null");
        event.setEventDate(Timestamp.valueOf(LocalDateTime.now()));
        event.setTitle(" for subscriptions");
        event.setStatus("");
        

        eventService.addEvent(event);
        return 1;
     }

    @GetMapping("event/{id}/reviews")
    public List<EventReviewDto> getEventReviews(@PathVariable("id") int id) {
        return eventReviewService.getReviewByEventId(id);
    }
    
    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getEvents(@RequestParam("page") @DefaultValue("0") int page) throws Exception {
        System.out.println("rtyrtry : ");
        return new ResponseEntity<>(eventService.getEvents(page, 6), HttpStatus.OK);
    }

    @GetMapping("/events/category/{categoryType}")
    public List<EventDto> getEventByCategoryType(@PathVariable("categoryType") String categoryType, @RequestParam("page") @DefaultValue("0") int page) throws Exception {
        System.out.println("categoryType : " + categoryType);
        return eventService.getEventByCategoryType(categoryType, page);
    }

    @GetMapping("/events/{speaker}")
    public List<EventDto> getEventBySpeaker(@PathVariable("speaker") String speaker, @RequestParam("page") @DefaultValue("0") int page) throws Exception {
        System.out.println("speaker : " + speaker);
        return eventService.getEventBySpeaker(speaker, page);
    }

    @GetMapping("/events/dates")
    public ResponseEntity<Page<EventByDateDto>> getEventsByDate(@RequestParam("date") String date, @RequestParam("page") int page, @RequestParam("size") int size) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM  y");
        LocalDate eventDate = LocalDate.parse(date, formatter);

        Page<EventByDateDto> eventPage = eventService.getEventsByDate(eventDate, PageRequest.of(page, size, Sort.by("eventDate").ascending()));

        return ResponseEntity.ok().body(eventPage);
    }

    @GetMapping("/events/new")
    public ResponseEntity<List<NewEventsDto>> getLastNewEvents() {
        List<NewEventsDto> events = eventService.getLastNewEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/event/{eventId}/tickets")
    public List<EventTicketDto> getEventTicketDetails(@PathVariable("eventId") int eventId) {
        List<EventTicketDto> eventTicketDto = eventTicketService.getEventTicketDetails(eventId);
        for (EventTicketDto e : eventTicketDto) {
            System.out.println(e);
        }
        return eventTicketService.getEventTicketDetails(eventId);
    }

    @GetMapping("/events/status/{status}")
    public List<EventDto> getEventByStatus(@PathVariable("status") String status, @RequestParam("page") @DefaultValue("0") int page) throws Exception {
        System.out.println("speaker : " + status);
        return eventService.getEventByStatus(status, page);
    }

    public static int calculatePaginationSize(int totalItems, int itemsPerPage) {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }

  
}

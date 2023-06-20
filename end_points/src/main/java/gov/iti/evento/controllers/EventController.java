package gov.iti.evento.controllers;

import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.services.EventReviewService;
import gov.iti.evento.services.EventTicketService;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.dtos.ticket.EventTicketDto;
import gov.iti.evento.services.util.exceptions.MessageException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class EventController {


    @Autowired
    private EventReviewService eventReviewService;
    @Autowired
    private EventTicketService eventTicketService;
    @GetMapping("event/{id}/reviews")
    public List<EventReviewDto> getEventReviews(@PathVariable("id")int id) {
        return eventReviewService.getReviewByEventId(id);
    }
    @Autowired
    EventService eventService;


    @GetMapping("/events")
    public List<EventDto> getEvents() {
        System.out.println("rtyrtry : ");
        return eventService.getAllEvents();
    }

    @GetMapping("/events/category/{categoryType}")
    public List<EventDto> getEventByCategoryType(@PathVariable("categoryType") String categoryType) throws MessageException {
        System.out.println("categoryType : " + categoryType);
        return eventService.getEventByCategoryType(categoryType);
    }

    @GetMapping("/events/speaker")
    public List<EventDto> getEventBySpeaker(@RequestParam("speaker") String speaker) throws MessageException {
        System.out.println("speaker : " + speaker);
        return eventService.getEventBySpeaker(speaker);
    }
    @GetMapping("/events/dates")
    public ResponseEntity<Page<EventByDateDto>> getEventsByDate(@RequestParam("date") String date,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size") int size) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM  y");
        LocalDate eventDate = LocalDate.parse(date, formatter);
        
        Page<EventByDateDto> eventPage = eventService.getEventsByDate(eventDate, PageRequest.of(page, size, Sort.by("eventDate").ascending()));
        return ResponseEntity.ok(eventPage);
    }
    @GetMapping("/event/{eventId}/tickets")
    public List<EventTicketDto> getEventTicketDetails( @PathVariable("eventId") int eventId) {
        List<EventTicketDto> eventTicketDto=eventTicketService.getEventTicketDetails(eventId);
        for (EventTicketDto e:eventTicketDto
             ) {
            System.out.println(e);
        }
        return eventTicketService.getEventTicketDetails(eventId);
    }

   
}

package gov.iti.evento.controllers;

import gov.iti.evento.entites.AvailableTicketId;
import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventReview;
import gov.iti.evento.entites.User;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.services.EventReviewService;
import gov.iti.evento.services.EventTicketService;
import gov.iti.evento.services.TicketBookingService;
import gov.iti.evento.services.dtos.*;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.ticket.EventTicketDto;
import gov.iti.evento.services.util.exceptions.MessageException;
import io.swagger.v3.core.util.Json;
import org.springframework.web.bind.annotation.RequestBody;
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
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private EventReviewService eventReviewService;

    @Autowired
    private EventTicketService eventTicketService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TicketBookingService ticketBookingService;
    @GetMapping("event/{id}/reviews")
    public List<EventReviewDto> getEventReviews(@PathVariable("id") int id) {
        System.out.println(id);
        return eventReviewService.getReviewByEventId(id);
    }

    @Autowired
    EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    public void m() {
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


    @GetMapping ("/events/{eventId}")
    public Optional<Event> getEventById (@PathVariable Integer eventId){
        return eventService.findEventById(eventId);
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
    @PostMapping(value = "events/{id}/review",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveReview (@RequestBody EventReviewCreateDto eventReview,@PathVariable("id")  Integer id) {
        System.out.println("review ->"+eventReview);
        System.out.println("id ->"+id);
        Optional<User> user =userRepository.findById(eventReview.getUser_id());

       if(user.isPresent()){
            // Check if the user has already reviewed the event
            boolean hasReviewed = eventReviewService.hasUserReviewedEvent(eventReview.getUser_id(), id);
            if (hasReviewed) {
                return ResponseEntity.badRequest().body("You have already reviewed this event.");
            }
            Optional <Event> event = eventService.findEventById(id);
            
            EventReview review = new EventReview();
            review.setReview(eventReview.getReview());
            review.setUser(user.get());
            review.setEvent(event.get());
            
            EventReview createdReview = eventReviewService.createReview(review);
            return ResponseEntity.ok(createdReview);
        }
        return ResponseEntity.badRequest().body("this user does not exist,login first.");
    }
    @GetMapping("/events/{id}/review")
    public ResponseEntity<Boolean> isReviewed (@RequestParam("user") int userId,@PathVariable("id") int eventId){
        System.out.println("user event  ->"+userId+" "+eventId );
        Boolean isExist = eventReviewService.hasUserReviewedEvent(userId, eventId);
        
        return ResponseEntity.ok(isExist);
    }
    @GetMapping("/events/status/{status}")
    public List<EventDto> getEventByStatus(@PathVariable("status") String status, @RequestParam("page") @DefaultValue("0") int page) throws Exception {
        System.out.println("speaker : " + status);
        return eventService.getEventByStatus(status, page);
    }

    public static int calculatePaginationSize(int totalItems, int itemsPerPage) {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }
//    @PostMapping(value = "event/{eventId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public boolean registerEvent(@PathVariable("eventId") int eventId,@RequestParam("userId") int userId,@RequestBody UserLoginDto user){
//        return true;
//    }

    @GetMapping("/events/{eventId}/availableTickets/{ticketId}")

    public int getNumberOfAvailableTickets(@PathVariable int eventId,@PathVariable int ticketId) {

        return eventTicketService.getNumberOfAvailableTickets(eventId,ticketId);
    }

    @PostMapping(value = "/events/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void bookTicket(@RequestBody UserTicketDto userTicketDto) {
        System.out.println("******");
         ticketBookingService.bookEvent(userTicketDto);
        System.out.println("******");

    }
}

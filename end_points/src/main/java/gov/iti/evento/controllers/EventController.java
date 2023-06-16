package gov.iti.evento.controllers;

import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.services.EventReviewService;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.util.exceptions.MessageException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private EventReviewService eventReviewService;
    @GetMapping("event/{id}/reviews")
    public List<EventReviewDto> getEventReviews(@PathVariable("id")int id) {
        return eventReviewService.getReviewByEventId(id);
    }
    EventService eventService;

    public void m() {
    }

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

}

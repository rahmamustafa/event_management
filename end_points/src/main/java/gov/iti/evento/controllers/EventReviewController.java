package gov.iti.evento.controllers;

import gov.iti.evento.services.EventReviewService;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class EventReviewController {

    EventReviewService eventReviewService;
    @Autowired
    public EventReviewController(EventReviewService eventReviewService){
        System.out.println("************");
        this.eventReviewService=eventReviewService;
    }

    @GetMapping("/{id}")
    public List<EventReviewDto> getEventReviews(@PathVariable("id")int id) {
        return eventReviewService.getReviewByEventId(id);
    }
}

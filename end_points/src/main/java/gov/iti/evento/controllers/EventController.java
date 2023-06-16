package gov.iti.evento.controllers;

import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.services.EventReviewService;
import gov.iti.evento.services.dtos.eventReviews.EventReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

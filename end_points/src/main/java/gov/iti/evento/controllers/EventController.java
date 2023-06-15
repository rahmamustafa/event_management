package gov.iti.evento.controllers;

import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EventService eventService;

    public void m() {
    }

    @GetMapping("/events")
    public List<EventDto> getEvents() {
        return eventService.getAllEvents();
    }

}

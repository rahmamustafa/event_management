package gov.iti.evento.controllers;

import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventByDateDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private EventService eventService;

    @GetMapping("/dates")
    public ResponseEntity<Page<EventByDateDto>> getEventsByDate(@RequestParam("date") String date,
                                                       @RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM  y");
        LocalDate eventDate = LocalDate.parse(date, formatter);
    
        System.out.println("eventDate ->"+eventDate);
                                                    
        Page<EventByDateDto> eventPage = eventService.getEventsByDate(eventDate, PageRequest.of(page, size, Sort.by("eventDate").ascending()));

        return ResponseEntity.ok(eventPage);
    }



}

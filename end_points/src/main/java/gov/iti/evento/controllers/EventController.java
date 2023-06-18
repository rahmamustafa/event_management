package gov.iti.evento.controllers;

import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.CategoryRepository;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.util.exceptions.MessageException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class EventController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    public void m() {
    }

    @GetMapping("/events")
    public ResponseEntity<List<EventDto>> getEvents(@RequestParam("page") @DefaultValue("0") int page, @RequestParam("size") @DefaultValue("6") int size) throws Exception {
        System.out.println("rtyrtry : ");
        int pagination_size = calculatePaginationSize((int) eventRepository.count(), 6);
        return new ResponseEntity<>(eventService.getEvents(page, pagination_size), HttpStatus.OK);
    }

    @GetMapping("/events/category/{categoryType}")
    public List<EventDto> getEventByCategoryType(@PathVariable("categoryType") String categoryType) throws Exception {
        System.out.println("categoryType : " + categoryType);
        return eventService.getEventByCategoryType(categoryType);
    }

    @GetMapping("/events/{speaker}")
    public List<EventDto> getEventBySpeaker(@PathVariable("speaker") String speaker) throws Exception {
        System.out.println("speaker : " + speaker);
        return eventService.getEventBySpeaker(speaker);
    }

    @GetMapping("/events/status/{status}")
    public List<EventDto> getEventByStatus(@PathVariable("status") String status) throws Exception {
        System.out.println("speaker : " + status);
        return eventService.getEventByStatus(status);
    }

    public static int calculatePaginationSize(int totalItems, int itemsPerPage) {
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }
}

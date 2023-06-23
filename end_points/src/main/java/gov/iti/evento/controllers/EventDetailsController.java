package gov.iti.evento.controllers;


import gov.iti.evento.entites.Event;
import gov.iti.evento.services.EventDetailService;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.dtos.EventoDetailesDTO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventDetails")
@CrossOrigin(origins = "http://localhost:4200")
public class EventDetailsController {

    private EventDetailService eventDetailService;

    @Autowired
    public EventDetailsController(EventDetailService eventDetailService) {
        this.eventDetailService = eventDetailService;
    }
    @GetMapping("/{eventId}")
    public EventoDetailesDTO getEvent(@PathVariable Integer eventId) {
        return eventDetailService.getEvent(eventId);
    }
//    @PostMapping("/{title}")
//    public Event savaEvent (String title){
//        Event event= new Event();
//        event.setDescription("en esto evento vamos a hablar sobre como el deporte puede afectar las emociones ");
////        event.setEventDate();
//        event.setTitle(title);
////        event.setCategory("deports");
//        event.setImage("../../../../resources/gallery-full-popup-sven.jpg");
//        return eventDetailService.(event);
//    }

    @GetMapping("/{eventId}/speakers")
    public List<SpeakersDto> getEventSpeakers(@PathVariable Integer eventId){
        return  eventDetailService.getEventSpeakers(eventId);
    }

}

package gov.iti.evento.controllers;


import gov.iti.evento.entites.Event;
import gov.iti.evento.services.EventDetailService;
import gov.iti.evento.services.dtos.EventoDetailesDTO;

import java.io.IOException;
import java.net.URL;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventDetails")
public class EventDetailsController {

    private EventDetailService eventDetailService;

    @Autowired
    public EventDetailsController(EventDetailService eventDetailService) {
        this.eventDetailService = eventDetailService;
    }
    @PostMapping("/{eventId}")
    public EventoDetailesDTO getEvent(@PathVariable Integer eventId) {
        return eventDetailService.getEvent(eventId);
    }
    @GetMapping("/{title}")
    public Event savaEvent (String title, Event event){
        event.setDescription("en esto evento vamos a hablar sobre como el deporte puede afectar las emociones ");
        //  String imagePath = "deportistas";
        event.setImage("http://2.bp.blogspot.com/-8AAEYfC6BD4/UgFHZq62UWI/AAAAAAAAAYw/SsybCfdzSnc/s1600/musica-deporte.jpg");
        return eventDetailService.savaEvent(event);
    }
   
}

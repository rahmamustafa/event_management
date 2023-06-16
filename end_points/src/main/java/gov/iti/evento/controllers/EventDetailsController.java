package gov.iti.evento.controllers;


import gov.iti.evento.services.EventDetailService;
import gov.iti.evento.services.dtos.EventoDetailesDTO;
import org.springframework.beans.factory.annotation.Autowired;
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


}

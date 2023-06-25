package gov.iti.evento.controllers;


import gov.iti.evento.services.EventCalenderService;
import gov.iti.evento.services.dtos.EventByDateDto;
import gov.iti.evento.services.dtos.EventCalenderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EventCalenderController {

    @Autowired
    EventCalenderService eventCalenderService;

    @GetMapping("calender/event/{eventTitle}")
    public Integer findUserIdByUserName (@PathVariable String eventTitle)throws Exception{
        return eventCalenderService.findEventByEventTitle(eventTitle);
    }
}
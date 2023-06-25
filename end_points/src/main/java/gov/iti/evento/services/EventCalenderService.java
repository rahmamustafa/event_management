package gov.iti.evento.services;


import gov.iti.evento.entites.Event;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.dtos.EventCalenderDto;
import gov.iti.evento.services.mappers.EventCalenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventCalenderService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    EventCalenderMapper eventCalenderMapper;

    private Integer id;


    public Integer findEventByEventTitle(String eventTitle)throws Exception{
        id =  eventRepository.findEventIdByEventTitle(eventTitle);
        return id;
    }
}
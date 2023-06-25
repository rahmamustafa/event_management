package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.repositories.SpeakerRepository;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.dtos.EventoDetailesDTO;
import gov.iti.evento.services.mappers.EventDisplayMapper;
import gov.iti.evento.services.mappers.SpeakerMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventDetailService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventDisplayMapper eventDisplayMapper;

    @Autowired
    private  SpeakerRepository speakerRepository;
    
    @Autowired
    private  EventSpeakerRepository eventSpeakerRepository;

   
    public EventoDetailesDTO getEvent(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));
        try {
            return eventDisplayMapper.eventToEventDetailsDTO(event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<SpeakersDto> getEventSpeakers(Integer eventId) {
//        event.setI;

        List<Speaker> eventSpeakers = speakerRepository.findAllEventSpeakers(eventId);
        List<SpeakersDto> speakersDtoList = new ArrayList<>();

        for (Speaker eventSpeaker : eventSpeakers) {
            SpeakersDto speakersDto = SpeakerMapper.INSTANCE.toSpeakersDto(eventSpeaker);
            speakersDtoList.add(speakersDto);
        }
        return speakersDtoList;
    }
   

    public Event saveEvent (Event event){
        return eventRepository.save(event);
    }
}



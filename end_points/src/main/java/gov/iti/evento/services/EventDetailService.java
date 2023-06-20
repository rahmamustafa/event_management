package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.services.dtos.EventSpeakersDto;
import gov.iti.evento.services.dtos.EventoDetailesDTO;
import gov.iti.evento.services.mappers.EventDisplayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventDetailService {
    private EventRepository eventRepository;
    @Autowired
    private EventDisplayMapper eventDisplayMapper;
    private final EventSpeakerRepository eventSpeakerRepository;

    @Autowired
    public EventDetailService(EventRepository eventRepository,
                              EventSpeakerRepository eventSpeakerRepository) {
        this.eventRepository = eventRepository;
        // this.eventDisplayMapper=eventDisplayMapper;
        this.eventSpeakerRepository = eventSpeakerRepository;
    }

    public EventoDetailesDTO getEvent(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event not found"));
        return eventDisplayMapper.eventToEventDetailsDTO(event);
    }

    public List<EventSpeakersDto> getEventSpeakers(Integer eventId) {
        List<EventSpeaker> eventSpeakers = eventSpeakerRepository.findByEventId(eventId);
        List<EventSpeakersDto> eventSpeakersDTOList = new ArrayList<>();

        for (EventSpeaker eventSpeaker : eventSpeakers) {
            EventSpeakersDto eventSpeakersDTO = new EventSpeakersDto();
            eventSpeakersDTO.setId(eventSpeaker.getSpeaker().getId());
            eventSpeakersDTO.setName(eventSpeaker.getSpeaker().getName());
            eventSpeakersDTO.setJobTitle(eventSpeaker.getSpeaker().getJobTitle());
            eventSpeakersDTO.setDescription(eventSpeaker.getSpeaker().getDescription());
            eventSpeakersDTO.setImage(eventSpeaker.getSpeaker().getImage());
            eventSpeakersDTOList.add(eventSpeakersDTO);
        }
        return eventSpeakersDTOList;
    }
}



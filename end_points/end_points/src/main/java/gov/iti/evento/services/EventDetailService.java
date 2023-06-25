package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.dtos.EventoDetailesDTO;
import gov.iti.evento.services.mappers.EventDisplayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventDetailService {
    private final EventSpeakerRepository eventSpeakerRepository;
    private final EventRepository eventRepository;
    @Autowired
    private EventDisplayMapper eventDisplayMapper;

    @Autowired
    public EventDetailService(EventRepository eventRepository, EventSpeakerRepository eventSpeakerRepository) {
        this.eventRepository = eventRepository;
        this.eventSpeakerRepository = eventSpeakerRepository;
    }

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

        List<EventSpeaker> eventSpeakers = eventSpeakerRepository.findByEvent(eventRepository.findById(eventId).get());
        List<SpeakersDto> speakersDtoList = new ArrayList<>();

        for (EventSpeaker eventSpeaker : eventSpeakers) {
            SpeakersDto speakersDto = convertToSpeakersDto(eventSpeaker);
            SpeakersDto eventSpeakersDTO = new SpeakersDto();
            eventSpeakersDTO.setId(eventSpeaker.getSpeaker().getId());
            eventSpeakersDTO.setName(eventSpeaker.getSpeaker().getName());
            eventSpeakersDTO.setJobTitle(eventSpeaker.getSpeaker().getJobTitle());
            eventSpeakersDTO.setDescription(eventSpeaker.getSpeaker().getDescription());
            speakersDtoList.add(eventSpeakersDTO);
        }
        return speakersDtoList;
    }

    private SpeakersDto convertToSpeakersDto(EventSpeaker eventSpeaker) {
        Integer speakerId = eventSpeaker.getSpeaker().getId();
        String speakerName = eventSpeaker.getSpeaker().getName();
        String jobTitle = eventSpeaker.getSpeaker().getJobTitle();
        String description = eventSpeaker.getSpeaker().getDescription();
//        byte[] image = eventSpeaker.getSpeaker().getImage();

        return new SpeakersDto(speakerId, speakerName, jobTitle, description);
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }
}



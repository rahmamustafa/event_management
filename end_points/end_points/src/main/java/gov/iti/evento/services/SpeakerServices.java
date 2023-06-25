package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.repositories.SpeakerRepository;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.mappers.EventSpeakersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class SpeakerServices {

    @Autowired
    SpeakerRepository speakerRepository;
    @Autowired
    EventSpeakerRepository eventSpeakerRepository;
   @Autowired
    EventRepository eventRepository;

    @Autowired
    EventSpeakersMapper eventSpeakersMapper;


    public SpeakerServices(){

    }
    public SpeakersDto saveSpeaker (Speaker speaker)throws Exception{
        speaker=speakerRepository.save(speaker);
        return eventSpeakersMapper.INSTANCE.toDto(speaker);
    }

    public SpeakersDto findById (Integer id)throws Exception{
        Optional<Speaker>speakerOptional = speakerRepository.findById(id);
        Speaker speaker= new Speaker();
        if (speakerOptional.isPresent()) {
            speaker = speakerOptional.get();
        }
        return eventSpeakersMapper.INSTANCE.toDto(speaker);
    }

    public Optional<Speaker> getSpeakerById (Integer id){
        return speakerRepository.findById(id);
    }

    public EventSpeaker saveEventSpeaker (EventSpeaker eventSpeaker){
        return eventSpeakerRepository.save(eventSpeaker);
    }
}

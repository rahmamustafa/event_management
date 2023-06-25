package gov.iti.evento.services;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.EventSpeaker;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventSpeakerRepository;
import gov.iti.evento.repositories.SpeakerRepository;
import gov.iti.evento.services.dtos.SpeakerAdminDto;
import gov.iti.evento.services.mappers.SpeakerAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SpeakerServices {

    @Autowired
    SpeakerRepository speakerRepository;
    @Autowired
    EventSpeakerRepository eventSpeakerRepository;
   @Autowired
    EventRepository eventRepository;


    public SpeakerServices(){

    }

    public Speaker saveSpeaker (Speaker speaker){
        return speakerRepository.save(speaker);
    }
//    public EventSpeaker addSpeakerToEvent (EventSpeaker eventSpeaker){
//        return eventSpeakerRepository.save(eventSpeaker);
//    }

    public Optional<Speaker> findById (Integer id){
        return speakerRepository.findById(id);
    }

    public EventSpeaker saveEventSpeaker (EventSpeaker eventSpeaker){
        return eventSpeakerRepository.save(eventSpeaker);
    }

}

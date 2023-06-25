package gov.iti.evento.services;

import java.util.ArrayList;
import java.util.List;

import gov.iti.evento.services.dtos.SpeakerAdminDto;
import gov.iti.evento.services.mappers.SpeakerAdminMapper;
import gov.iti.evento.services.speaker.SpeakerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.SpeakerRepository;
import gov.iti.evento.services.dtos.SpeakerMostSpeakingDto;
import gov.iti.evento.services.mappers.SpeakerMapper;

@Service
public class SpeakerService {
    @Autowired
    private SpeakerRepository speakerRepository;

    public List<SpeakerMostSpeakingDto> getMostSpeakingSpeakersList() {

        List<Speaker> speakers= speakerRepository.findTop8ByEventCount();
        
        return speakers.stream().map(SpeakerMapper.INSTANCE::toDto).toList();
    }

    public List<SpeakerDto> getAllSpeakerName() {
        List<Speaker> speakers= speakerRepository.findAll();
        return speakers.stream().map(SpeakerMapper.INSTANCE::toSpeakerDto).toList();
    }
    public List<SpeakerAdminDto> getAllSpeakers() throws Exception {
        List<Speaker> speakers = speakerRepository.findAll();
        List<SpeakerAdminDto> list = new ArrayList<>();
        for (Speaker speaker : speakers) {
            SpeakerAdminDto speakerAdminDto = SpeakerAdminMapper.INSTANCE.toDto(speaker);
            list.add(speakerAdminDto);
        }
        return list;
    }
}

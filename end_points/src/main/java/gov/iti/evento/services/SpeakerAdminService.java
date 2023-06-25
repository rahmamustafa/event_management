package gov.iti.evento.services;

import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.SpeakerRepository;
import gov.iti.evento.services.dtos.AddSpeakerDto;
import gov.iti.evento.services.dtos.SpeakerAdminDto;
import gov.iti.evento.services.mappers.SpeakerAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakerAdminService {

    @Autowired
    SpeakerRepository speakerRepository;
    @Autowired
    SpeakerAdminMapper speakerAdminMapper;

    public void addSpeakerByAdmin (AddSpeakerDto speakerDto) throws Exception {
        Speaker speaker = speakerRepository.save(speakerAdminMapper.INSTANCE.toEntity(speakerDto));
        System.out.println(speaker.getId());
        // return speakerAdminMapper.INSTANCE.toDto(speakerAdminDto);
    }
}
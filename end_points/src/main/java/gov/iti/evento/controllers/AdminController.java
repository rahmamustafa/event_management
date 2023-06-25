package gov.iti.evento.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.iti.evento.entites.Speaker;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.FileStorageService;
import gov.iti.evento.services.SpeakerAdminService;
import gov.iti.evento.services.dtos.AddSpeakerDto;
import gov.iti.evento.services.dtos.SpeakerAdminDto;
import gov.iti.evento.services.dtos.SpeakersDto;
import gov.iti.evento.services.dtos.event.AddEventDto;
import gov.iti.evento.services.util.enums.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final FileStorageService fileStorageService;
    private final ObjectMapper objectMapper;
    private final EventService eventService;
    @Autowired
    SpeakerAdminService speakerAdminService;

    @PostMapping("/events")
    public void addEvent(@RequestParam("image") MultipartFile eventImage
            , @RequestParam("event") String event) throws JsonProcessingException {

        AddEventDto eventDto;
        eventDto = objectMapper.readValue(event, AddEventDto.class);
        String userImageName = fileStorageService.storeFile(eventImage, eventDto.getTitle(), "Events");
        eventDto.setImage(userImageName);
        eventDto.setStatus(EventStatus.valueOf(EventStatus.PENDING.toString()));
        eventService.saveEvent(eventDto);
        System.out.println(event);
        System.out.println(eventDto);

    }

    @PostMapping("/event-title/exist")
    public boolean checkEmailValid(@RequestBody String title) {
        return eventService.checkTitleValid(title);
    }

    @PostMapping("/speaker")
    public void saveSpeaker(@RequestParam("image") MultipartFile speakerImage
            , @RequestParam("speaker") String speaker) throws Exception {
        AddSpeakerDto speakerDto;
        speakerDto = objectMapper.readValue(speaker, AddSpeakerDto.class);
        String speakerImageName = fileStorageService.storeFile(speakerImage, speakerDto.getName(), "Speakers");
        speakerDto.setImage(speakerImageName);
        speakerAdminService.addSpeakerByAdmin(speakerDto);
        System.out.println(speakerDto);
        // return speakerAdminService.addSpeakerByAdmin(speaker1);
    }

}
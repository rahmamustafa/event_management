package gov.iti.evento.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.services.EventService;
import gov.iti.evento.services.FileStorageService;
import gov.iti.evento.services.dtos.event.AddEventDto;
import gov.iti.evento.services.util.enums.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apii")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final FileStorageService fileStorageService;
    private final ObjectMapper objectMapper;
    private final EventService eventService;
    @PostMapping("/events")
    public void addEvent(@RequestParam("image") MultipartFile eventImage
            , @RequestParam("event") String event) throws JsonProcessingException {

        AddEventDto eventDto;
        eventDto = objectMapper.readValue(event, AddEventDto.class);
        String userImageName = fileStorageService.storeFile(eventImage , eventDto.getTitle() , "Events");
        eventDto.setImage(userImageName);
        eventDto.setStatus(EventStatus.valueOf(EventStatus.PENDING.toString()));
        eventService.saveEvent(eventDto);
        System.out.println(event);
        System.out.println(eventDto);

    }
}

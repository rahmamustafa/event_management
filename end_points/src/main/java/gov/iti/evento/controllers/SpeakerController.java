package gov.iti.evento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.iti.evento.entites.Speaker;
import gov.iti.evento.services.SpeakerService;
import gov.iti.evento.services.dtos.SpeakerMostSpeakingDto;

@RestController
@RequestMapping("/speakers")
public class SpeakerController {
     @Autowired
    private SpeakerService speakerService;

    @GetMapping("/most-speaking")
     public ResponseEntity<List<SpeakerMostSpeakingDto>> getMostSpeakingSpeakers() {
        List<SpeakerMostSpeakingDto> speakers = speakerService.getMostSpeakingSpeakersList();
        return ResponseEntity.ok(speakers);
    }
}

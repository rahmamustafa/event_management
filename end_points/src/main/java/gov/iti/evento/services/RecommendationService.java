package gov.iti.evento.services;

import com.fasterxml.jackson.annotation.OptBoolean;
import gov.iti.evento.entites.*;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.UserInterestRepository;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.services.dtos.RecommendationDto;
import gov.iti.evento.services.mappers.RecommendationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RecommendationService {

    @Autowired
    RecommendationMapper recommendationMapper;
    @Autowired
    RecommendationDto recommendationDto;
    @Autowired
    UserInterest userInterest;
    @Autowired
    UserInterestRepository userInterestRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserInterestId userInterestId;

    @Autowired
    EventRepository eventRepository;


    public UserInterest saveUserInterest (Integer userId, Integer eventId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Event>eventOptional = eventRepository.findById(eventId);
        User user = new User();
        Event event = new Event();
        if(userOptional.isPresent() && eventOptional.isPresent()){
            user = userOptional.get();
            event = eventOptional.get();
        }
        userInterestId.setUserId(userId);
        userInterestId.setEventId(eventId);
        userInterest.setEvent(event);
        userInterest.setUser(user);
       return userInterestRepository.save(userInterest);
    }

//   git 
}

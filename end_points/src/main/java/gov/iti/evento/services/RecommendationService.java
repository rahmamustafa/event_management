package gov.iti.evento.services;

//import com.fasterxml.jackson.annotation.OptBoolean;
import gov.iti.evento.entites.*;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.EventReviewRepository;
import gov.iti.evento.repositories.UserInterestRepository;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.dtos.RecommendationDto;
import gov.iti.evento.services.mappers.RecommendationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    @Autowired
    RecommendationDto recommendationDto;
    @Autowired
    RecommendationMapper recommendationMapper;

    //    @Autowired
//    UserInterest userInterest;
    @Autowired
    UserInterestRepository userInterestRepository;
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    UserInterestId userInterestId;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventReviewRepository eventReviewRepository;
    public RecommendationService (){}


    public UserInterest saveUserInterest (Integer userId, Integer eventId){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Event>eventOptional = eventRepository.findById(eventId);
        User user = new User();
        Event event = new Event();
        UserInterestId userInterestId= new UserInterestId();
        UserInterest userInterest= new UserInterest();
        if(userOptional.isPresent() && eventOptional.isPresent()) {
            user = userOptional.get();
            event = eventOptional.get();
            userInterestId.setUserId(userId);
            userInterestId.setEventId(eventId);
            System.out.println(userInterestId);
            userInterest.setEvent(event);
            userInterest.setUser(user);
            System.out.println(userInterest);
        }
        return userInterest;
    }

    public List<RecommendationDto> getUserInterests (Integer userId) throws Exception{
        List<Event> events = userInterestRepository.findInterestsByUserId(userId);
        List<EventDto> eventDtos = new ArrayList<>();
        List<RecommendationDto> recommendationDtos = new ArrayList<>();
        for (Event event : events)
        {
            recommendationDto = recommendationMapper.INSTANCE.toDto(event);
            recommendationDtos.add(recommendationDto);
        }
        return recommendationDtos;
    }

    public List <Category> getUserInterestCategoryByUserId(Integer userId){
        List<Event> events = userInterestRepository.findInterestsByUserId(userId);
        List<Category> categories = new ArrayList<>();
        for(Event event: events){
            Category category = event.getCategory();
            categories.add(category);
        }
        return categories;
    }

    public List <Category> getUserEventsCategoryByUserId(Integer userId){
        List<Event> events = eventReviewRepository.findEventByUserId(userId);
        List<Category> categories = new ArrayList<>();
        for(Event event: events){
            Category category = event.getCategory();
            categories.add(category);
        }
        return categories;
    }

    public List<RecommendationDto> getUserRecommendationByUserId (Integer userId) throws Exception{
        List<Category> userEventCategories = getUserEventsCategoryByUserId(userId);
        List<Category> userInterestCategories = getUserInterestCategoryByUserId(userId);
//        List <Category> recommendedCategories= new ArrayList<>();
        List<RecommendationDto> recommendationDtos = new ArrayList<>();
        List<Event>events = new ArrayList<>();
        for (Category category: userEventCategories){
            events= eventRepository.findByCategoryType(category.getType());
//            System.out.println(events);
        }
        for (Category category: userInterestCategories){
            events= eventRepository.findByCategoryType(category.getType());
//            System.out.println(events);
        }
        for (Event event: events){
            recommendationDto= recommendationMapper.INSTANCE.toDto(event);
            recommendationDtos.add(recommendationDto);
        }
//        System.out.println(recommendationDtos);
        return recommendationDtos;
    }



}
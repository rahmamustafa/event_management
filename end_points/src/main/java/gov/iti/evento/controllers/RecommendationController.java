package gov.iti.evento.controllers;

import gov.iti.evento.entites.UserInterest;
import gov.iti.evento.services.RecommendationService;
import gov.iti.evento.services.dtos.RecommendationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RecommendationController {

    @Autowired
    RecommendationService recommendationService;
    @GetMapping("recommendation/{userId}")
    public List<RecommendationDto>  getUserRecommendationByUserId (@PathVariable Integer userId) throws Exception{
        return recommendationService.getUserRecommendationByUserId(userId);
    }
    @PostMapping("/recommendation/{userId}/{eventId}")
    public UserInterest saveUserInterest (@PathVariable Integer userId,@PathVariable Integer eventId) throws Exception{
        return recommendationService.saveUserInterest(userId, eventId);
    }


}
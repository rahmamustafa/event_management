package gov.iti.evento.controllers;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.User;
import gov.iti.evento.services.AnalyticsServices;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.util.converters.AgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AnalyticsControllers {
    @Autowired
    AnalyticsServices analyticsServices;

    @GetMapping("/events/attendance")
    public Map<String, Long> getEventAttendance() {
        return analyticsServices.getEventAttendance();
    }

    @GetMapping("/api/admin/gender")
    public Map<String, Long> getGenderAnalysis() {
        return analyticsServices.getGenderAnalysis();
    }

    @GetMapping("/users")
    public Long getAgeAnalysis(@RequestParam("minAge") int minAge, @RequestParam("maxAge") int maxAge) {
        System.out.println(minAge + " Age " + maxAge);
        LocalDate startDate = AgeUtils.getStartDateForAgeRange(minAge);
        LocalDate endDate = AgeUtils.getEndDateForAgeRange(maxAge);

        System.out.println(startDate + " " + endDate);
        return analyticsServices.getAgeAnalysis(startDate, endDate);
    }

    @GetMapping("/api/admin/country")
    public Map<String, Long> getCountryAnalysis() {
        return analyticsServices.getCountryAnalysis();
    }

    @GetMapping("/api/admin/popular")
    public List<EventDto> getPopularEvents() throws Exception {
        return analyticsServices.getPopularEvents();
    }

}

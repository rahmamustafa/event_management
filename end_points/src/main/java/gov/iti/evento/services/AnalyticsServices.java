package gov.iti.evento.services;

import gov.iti.evento.entites.*;
import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.repositories.UserTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsServices {
    @Autowired
    UserTicketRepository userTicketRepository;
    @Autowired
    UserRepository userRepository;

    public Map<String, Long> getEventAttendance() {
        List<UserTicket> userTickets = userTicketRepository.findAll();
        Map<String, Long> usersPerEvent = userTickets.stream()
                .collect(Collectors.groupingBy(
                        userTicket -> (userTicket.getEventTicket().getEvent().getTitle()),
                        Collectors.counting()
                ));
        usersPerEvent.forEach((eventId, userCount) -> {
            System.out.println("Event Title: " + eventId + ", User Count: " + userCount);
        });
        return usersPerEvent;
    }

    public Map<String, Long> getGenderAnalysis() {
        List<User> users = userRepository.findAll();
        Map<String, Long> usersPerGender = users.stream()
                .collect(Collectors.groupingBy(
                        userGender -> (userGender.getGender()),
                        Collectors.counting()
                ));
        return usersPerGender;
    }

    public Long getAgeAnalysis(LocalDate maxAge, LocalDate minAge) {
        Long usersCount = userRepository.countByBirthDateBetween(minAge, maxAge);
        return usersCount;
    }

    public Map<String, Long> getCountryAnalysis() {
        List<User> users = userRepository.findAll();
        Map<String, Long> usersPerGender = users.stream()
                .collect(Collectors.groupingBy(
                        userGender -> (userGender.getCountry()),
                        Collectors.counting()
                ));
        return usersPerGender;
    }

    public List<String> getPopularEvents() {
        List<String> sortedKeys = new ArrayList<>(getEventAttendance().keySet());
        Collections.sort(sortedKeys);
        Collections.reverse(sortedKeys);
        return sortedKeys;
    }

}

package gov.iti.evento.services;

import gov.iti.evento.entites.*;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.repositories.UserTicketRepository;
import gov.iti.evento.services.dtos.EventDto;
import gov.iti.evento.services.mappers.EventMapper;
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
    @Autowired
    EventMapper eventMapper;

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

    public List<EventDto> getPopularEvents() throws Exception {
        List<UserTicket> userTickets = userTicketRepository.findAll();
        Map<Event, Long> popularEvents = userTickets.stream()
                .collect(Collectors.groupingBy(
                        userTicket -> (userTicket.getEventTicket().getEvent()),
                        Collectors.counting()
                ));
        Map<Event, Long> sortedPopularEvents = popularEvents.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
        List<Event> events = sortedPopularEvents.keySet().stream().toList();
        List<EventDto> pop_event = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = eventMapper.INSTANCE.toDto(event);
            pop_event.add(eventDto);
        }
        sortedPopularEvents.forEach((eventId, userCount) -> {
            System.out.println("Popular Event : " + eventId + ", User Count: " + userCount);
        });
        return pop_event;
    }

}

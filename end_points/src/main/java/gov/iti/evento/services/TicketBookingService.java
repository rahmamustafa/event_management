package gov.iti.evento.services;

import gov.iti.evento.entites.*;
import gov.iti.evento.repositories.AvailableTicketRepository;
import gov.iti.evento.repositories.EventTicketRepository;
import gov.iti.evento.repositories.UserRepository;
import gov.iti.evento.repositories.UserTicketRepository;
import gov.iti.evento.services.dtos.UserTicketDto;
import gov.iti.evento.services.mappers.UserTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingService {
    @Autowired
    private EventTicketService eventTicketService;
    @Autowired
    private AvailableTicketRepository availableTicketRepository;
    @Autowired
    private UserTicketRepository userTicketRepository;
    @Autowired
    private EventTicketRepository eventTicketRepository;
    @Autowired
    private UserTicketMapper userTicketMapper;
    @Autowired
    private UserRepository userRepository;
    public void bookEvent(UserTicketDto userTicketDto) {
        int availableTickets=getAvailableTickets(userTicketDto.getEventId(),userTicketDto.getTicketId());

        availableTickets-=userTicketDto.getQuantity();

        availableTicketRepository.updateAvailableTicketsById(availableTickets,new AvailableTicketId(userTicketDto.getTicketId(),
                userTicketDto.getEventId()));
        User user=userRepository.getById(userTicketDto.getUserId());

        if(!userTicketRepository.existsById(new UserTicketId(userTicketDto.getUserId(),userTicketDto.getTicketId()))) {
            System.out.println("existttttttttttttttttt");

            UserTicket userTicket = userTicketMapper.toEntity(userTicketDto);
            userTicket.setUser(user);

            userTicket.setId(new UserTicketId(userTicketDto.getUserId(),eventTicketRepository.getIdById(new EventTicketId(userTicketDto.getEventId(),userTicketDto.getTicketId()))));
            System.out.println(eventTicketRepository.getIdById(new EventTicketId(userTicketDto.getEventId(),userTicketDto.getTicketId())));
            EventTicket eventTicket=eventTicketRepository.getEventTicketByEventTicketId(new EventTicketId(userTicketDto.getEventId(),userTicketDto.getTicketId()));
            userTicket.setEventTicket(eventTicket);
            System.out.println(eventTicket);
            userTicketRepository.save(userTicket);
        }
        else {
            userTicketRepository.updateQuantityById(userTicketDto.getQuantity(),
                    new UserTicketId(userTicketDto.getUserId(),userTicketDto.getTicketId()));
        }
//        if(userTicketRepository.existsById(new UserTicketId(userTicketDto.getUserId(),)))
//            return  true;

    }

    public int getAvailableTickets(int eventId,int ticketId){
        return eventTicketService.getNumberOfAvailableTickets(eventId, ticketId);

    }
//    public void updateAvailableTickets(int availableTickets,int ticketId,int userId){
//        AvailableTicket availableTicket=new AvailableTicket();
//        availableTicketRepository.updateAvailableTicketsById(availableTickets);
//    }

}

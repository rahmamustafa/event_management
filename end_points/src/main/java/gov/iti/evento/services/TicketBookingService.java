package gov.iti.evento.services;

import gov.iti.evento.entites.AvailableTicket;
import gov.iti.evento.entites.AvailableTicketId;
import gov.iti.evento.entites.EventTicketId;
import gov.iti.evento.entites.UserTicketId;
import gov.iti.evento.repositories.AvailableTicketRepository;
import gov.iti.evento.repositories.EventTicketRepository;
import gov.iti.evento.repositories.UserTicketRepository;
import gov.iti.evento.services.dtos.UserTicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketBookingService {
    @Autowired
    EventTicketService eventTicketService;
    @Autowired
    AvailableTicketRepository availableTicketRepository;
    @Autowired
    UserTicketRepository userTicketRepository;
    @Autowired
    EventTicketRepository eventTicketRepository;
    public boolean bookEvent(UserTicketDto userTicketDto) {
        int availableTickets=getAvailableTickets(userTicketDto.getEventId(),userTicketDto.getTicketId());
        if (availableTickets<userTicketDto.getQuantity() )
            return false;

        availableTickets-=userTicketDto.getQuantity();

        availableTicketRepository.updateAvailableTicketsById(availableTickets,new AvailableTicketId(userTicketDto.getTicketId(),
                userTicketDto.getEventId()));
//        if(userTicketRepository.existsById(new UserTicketId(userTicketDto.getUserId(),)))
//            return  true;
        return true;
    }

    public int getAvailableTickets(int eventId,int ticketId){
        return eventTicketService.getNumberOfAvailableTickets(eventId, ticketId);

    }
//    public void updateAvailableTickets(int availableTickets,int ticketId,int userId){
//        AvailableTicket availableTicket=new AvailableTicket();
//        availableTicketRepository.updateAvailableTicketsById(availableTickets);
//    }

}

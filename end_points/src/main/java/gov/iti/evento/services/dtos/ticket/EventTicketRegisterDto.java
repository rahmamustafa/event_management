package gov.iti.evento.services.dtos.ticket;

import gov.iti.evento.services.dtos.ticket.TicketDto;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.evento.entites.EventTicket}
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventTicketRegisterDto implements Serializable {
//    Integer eventTicketIdEventId;
//    Integer eventTicketIdTicketId;
    TicketDto ticket;
    Float price;

}
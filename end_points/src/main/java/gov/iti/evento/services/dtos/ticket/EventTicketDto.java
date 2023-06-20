package gov.iti.evento.services.dtos.ticket;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link gov.iti.evento.entites.EventTicket}
 */
@Value
public class EventTicketDto implements Serializable {
    TicketDto ticket;
    Float price;
}
package gov.iti.evento.repositories;

import gov.iti.evento.entites.EventTicket;

/**
 * Projection for {@link gov.iti.evento.entites.UserTicket}
 */
public interface UserTicketInfo {
    EventTicket getEventTicket();
}
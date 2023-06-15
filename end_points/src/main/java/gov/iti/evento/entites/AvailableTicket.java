package gov.iti.evento.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "available_tickets")
public class AvailableTicket {
    @EmbeddedId
    private AvailableTicketId id;

    @MapsId("ticketId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @MapsId("eventId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "available_tickets")
    private Integer availableTickets;

    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id1;

}